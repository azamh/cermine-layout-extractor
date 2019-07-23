## How can extract layout file in java environment:
```java
public class CallExtractor
{
	public static void cermine_line_layout_extractor( String inputDir, String outputDir, String pdfFileSizeLimit )
			throws IOException, AnalysisException
	{
		String[] args1 = new String[3];
		args1[0] = inputDir;
		args1[1] = outputDir;
		args1[2] = pdfFileSizeLimit;
		CermineLineLayoutExtractor.main( args1 );
	}

	public static void main( String[] args ) throws IOException, AnalysisException
	{    
		String inputDir = ".../pdfs/";
		String outputDir = ".../layouts/";
		String pdfFileSizeLimit = "90000000";

		cermine_line_layout_extractor( inputDir, outputDir, pdfFileSizeLimit );
	}
}

```

## How can call Java Maven in Python environment:
```python
def extractLayoutByCermine():
    command = ''
    pathOfPdfs = config_url_pdfs() + '/'
    pathOfLayouts = config_url_Layouts() + '/'
    try:
        print(command)
        os.chdir(config_url_layout_extractor())
        command = 'mvn exec:java -Dexec.mainClass="gesis.cermine.layout.extractor.CermineLineLayoutExtractor" '
        command += '-Dexec.args="'
        command += pathOfPdfs + '/ '
        command += pathOfLayouts + '/ '
        command += '90000000'
        command += '"'
        print(command)
        proc = subprocess.Popen([command], stdout=subprocess.PIPE, shell=True)
        (output, err) = proc.communicate()
        print(output)
    except Exception as e:
        logFile.write("Error: {0}, {1}, command: {2} ,{3} \n".format(str(
            datetime.datetime.now().date()), str(datetime.datetime.now().time()), command, e.args))
        print(e.args)
    return command

```