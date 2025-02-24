package gesis.cermine.layout.extractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import pl.edu.icm.cermine.ComponentConfiguration;
import pl.edu.icm.cermine.ExtractionUtils;
import pl.edu.icm.cermine.exception.AnalysisException;
import pl.edu.icm.cermine.structure.ITextCharacterExtractor;
import pl.edu.icm.cermine.structure.model.BxDocument;

/**
 * Class for only executing the needed CERMINE extractors to improve the runtime
 * in comparison with calling ContentExtractor methods which executes the full
 * CERMINE pipeline.
 */

public class CerminePdfExtractor
{
	private ComponentConfiguration componentConfig;

	public CerminePdfExtractor( ComponentConfiguration componentConfiguration ) throws AnalysisException
	{
		this.componentConfig = componentConfiguration;
	}

	/**
	 * Executes character extraction, page segmentation, and reading order
	 * resolving methods.
	 *
	 * @param pdfInputFile
	 * @return bxDocument
	 * @throws AnalysisException
	 * @throws IOException
	 */
	public BxDocument extractWithResolvedReadingOrder( File pdfInputFile ) throws AnalysisException, IOException
	{
		InputStream inputStream = new FileInputStream( pdfInputFile );

		ITextCharacterExtractor iTextCharacterExtractor = new ITextCharacterExtractor();
		// set page limits to override the default limits
		iTextCharacterExtractor.setPagesLimits( -1, -1 );
		BxDocument bxDocument = iTextCharacterExtractor.extractCharacters( inputStream );

		bxDocument = ExtractionUtils.segmentPages( this.componentConfig, bxDocument );
		bxDocument = ExtractionUtils.resolveReadingOrder( this.componentConfig, bxDocument );
		inputStream.close();
		return bxDocument;
	}
}
