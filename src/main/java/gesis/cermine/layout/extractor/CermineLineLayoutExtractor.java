package gesis.cermine.layout.extractor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;

import pl.edu.icm.cermine.ComponentConfiguration;
import pl.edu.icm.cermine.exception.AnalysisException;
import pl.edu.icm.cermine.structure.DocstrumSegmenter;
import pl.edu.icm.cermine.structure.HierarchicalReadingOrderResolver;
import pl.edu.icm.cermine.structure.model.BxLine;

/**
 * Extension of {@link CermineLineExtractor} to also store layout information of
 * the individual lines as CSVs.
 */
public class CermineLineLayoutExtractor extends CermineLineExtractor
{
	/**
	 *
	 * @param args
	 *            args[0]: directory containing pdf files and/or subfolders with
	 *            pdf files </br>
	 *            args[1]: directory in which the outputfiles are stored,
	 *            including the subdirectories args[2]: long that specifies the
	 *            maximum pdf file size in bytes to prevent heap space errors
	 *            args[2]: file size limit of pdfs to be processed, in bytes
	 * 
	 *
	 * @throws IOException
	 * @throws AnalysisException
	 */
	public static void main( String[] args ) throws IOException, AnalysisException
	{
		File inputDir = new File( args[0] );
		File outputDir = new File( args[1] );
		long pdfFileSizeLimit = Long.parseLong( args[2] );

		if ( !outputDir.exists() )
		{
			outputDir.mkdirs();
		}

		ComponentConfiguration componentConfiguration = new ComponentConfiguration();
		CermineLineLayoutExtractor cermineLineLayoutExtractor = new CermineLineLayoutExtractor( componentConfiguration );

		List<File> inputFiles = FileUtils.listFilesRecursively( inputDir );

		Instant start = Instant.now();
		for ( File inputFile : inputFiles )
		{
			System.out.println( "processing: " + inputFile );
			if ( inputFile.length() > pdfFileSizeLimit )
			{
				System.out.println( "skipping with size: " + inputFile.length() );
				continue;
			}

			String subDirectories = inputFile	.getParentFile()
												.getAbsolutePath()
												.replace( "\\", "/" )
												.replaceFirst( inputDir	.getAbsolutePath()
																		.replace( "\\", "/" ), "" );
			File currentOutputDirectory = new File( outputDir.getAbsolutePath() + File.separator + subDirectories );

			String outputFileName = FilenameUtils.removeExtension( inputFile.getName() ) + ".csv";
			File outputFile = new File( currentOutputDirectory + File.separator + outputFileName );

			// skip computation if outputFile already exists
			if ( outputFile.exists() )
			{
				continue;
			}
			if ( !currentOutputDirectory.exists() )
			{
				currentOutputDirectory.mkdirs();
			}

			List<String> lines = cermineLineLayoutExtractor.extract( inputFile );
			BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter( outputFile ) );
			for ( String line : lines )
			{
				if ( line.split( "\\t" ).length == 7 )
				{
					bufferedWriter.write( line );
					bufferedWriter.newLine();
				}
			}
			bufferedWriter.close();

			Runtime runtime = Runtime.getRuntime();
			runtime.gc();

			long usedMemory = runtime.totalMemory() - runtime.freeMemory();
			double usedMegabytes = usedMemory / (1024L * 1024L);
			System.out.println( "Used memory is megabytes: " + usedMegabytes );

			// reset DocumentSegmenter and ReadingOderResolver to fix memory
			// leak
			componentConfiguration.setDocumentSegmenter( new DocstrumSegmenter() );
			componentConfiguration.setReadingOrderResolver( new HierarchicalReadingOrderResolver() );
		}

		Instant end = Instant.now();
		System.out.println( "Done. Execution time: " + Duration.between( start, end ) );
	}

	public CermineLineLayoutExtractor( ComponentConfiguration componentConfiguration ) throws AnalysisException
	{
		super( componentConfiguration );
	}

	/**
	 * TODO add explanation to x/y/height/width/zoneID
	 */
	@Override
	protected String extractFromLine( BxLine bxLine )
	{
		String fixedLine = TextUtils.fixAccents( bxLine.toText() );
		fixedLine = CsvUtils.normalize( fixedLine );

		Set<String> fontNames = bxLine.getFontNames();
		String fontValue = "";
		if ( !fontNames.isEmpty() )
		{
			fontValue = fontNames	.iterator()
									.next();
		}

		fixedLine += "\t" + bxLine.getX() + "\t" + bxLine.getY() + "\t" + bxLine.getHeight() + "\t" + bxLine.getWidth()
				+ "\t" + bxLine	.getParent()
								.getId()
				+ "\t" + fontValue;
		return fixedLine;
	}
}
