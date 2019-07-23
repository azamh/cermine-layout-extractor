package gesis.cermine.layout.extractor;

import java.io.IOException;

import pl.edu.icm.cermine.exception.AnalysisException;

public class CallExtractor
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

	public static void main( String[] args ) throws IOException, AnalysisException
	{
		// mvn exec:java
		// -Dexec.mainClass="gesis.cermine.layout.extractor.CermineLineLayoutExtractor"
		// -Dexec.args="
		// /export/home/ahosseini/excite-WebAPI-venu/docker/Data/1-pdfs/
		// /export/home/ahosseini/excite-WebAPI-venu/docker/Data/2-layouts/
		// 90000000"
		// String inputDir = "C:/Azam Files/Test/pdf/";
		// String outputDir = "C:/Azam Files/Test/result/";
		// String pdfFileSizeLimit = "90000000";

		String inputDir = "/export/home/ahosseini/excite-WebAPI-venu/docker/Data/1-pdfs/";
		String outputDir = "/export/home/ahosseini/excite-WebAPI-venu/docker/Data/2-layouts/";
		String pdfFileSizeLimit = "90000000";

		// String inputDir = args[0];
		// String outputDir = args[1];
		// String pdfFileSizeLimit = args[2];

		cermine_line_layout_extractor( inputDir, outputDir, pdfFileSizeLimit );
	}

}
