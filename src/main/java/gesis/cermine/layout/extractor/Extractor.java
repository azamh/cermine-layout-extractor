package gesis.cermine.layout.extractor;

import java.io.IOException;

import pl.edu.icm.cermine.exception.AnalysisException;

/**
 * Hello world!
 *
 */
public class Extractor 
{
	public static void cermine_line_layout_extractor( String inputDir, String outputDir, String pdfFileSizeLimit )
			throws IOException, AnalysisException
	{
		String[] args1 = new String[3];
		args1[0] = inputDir;// "C:/Azam Files/Test/pdf/"
		args1[1] = outputDir;// "C:/Azam Files/Test/result/";
		args1[2] = pdfFileSizeLimit;// "90000000"
		CermineLineLayoutExtractor.main( args1 );
	}

	 public static void main( String[] args ) throws IOException,
	 AnalysisException
	 {
	
	 String inputDir = "C:/Azam Files/Test/pdf/";
	 String outputDir = "C:/Azam Files/Test/result/";
	 String pdfFileSizeLimit = "90000000";
	 cermine_line_layout_extractor( inputDir, outputDir, pdfFileSizeLimit );
	 }

//	public static void main( String[] args ) throws IOException, AnalysisException
//	{
//		System.out.println( args[0] );
//		System.out.println( args[1] );
//		System.out.println( args[2] );
//		System.out.println( args[3] );
//
//		String funcName = args[0].trim();// -Dexec.args="CermineLineExtractor.main
//		if ( funcName.equals( "cerminelinelayoutextractor" ) )
//			cermine_line_layout_extractor( args[1], args[2], args[3] );
//		else
//			System.out.println( "First Arguman is: " + args[0] );
	// }
}
