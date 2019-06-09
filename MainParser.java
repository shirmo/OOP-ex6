import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainParser {
    private final String path;
    private String corpusPath;
    private String indexerCondition;
    private String parseRule;
    private String queryText;
    private static String[] Headers = {"CORPUS", "INDEXER", "PARSE_RULE", "QUERY"};
   // enum IndexerContent {DICT, NAIVE, NAIVE_RK, SUFFIX_TREE, CUSTOM}

    public MainParser(String args) {
        this.path = args;
        fileIterator();
    }

    private void fileIterator(){
        String title;
        try {
            BufferedReader file = new BufferedReader(new FileReader(this.path));
            title = file.readLine();
            if (title == null || !title.equals(Headers[0])){
                throw new ParsingException("ERROR: Problem with file format.");
            }
            else {
                corpusPath = file.readLine();
                title = file.readLine();
            }
            if(title == null || !title.equals(Headers[1])){
                throw new ParsingException("ERROR: Problem with file format.");
            }
            else {
                indexerCondition = file.readLine();
                title = file.readLine();
            }
            if(title == null || !title.equals(Headers[2])){
                throw new ParsingException("ERROR: Problem with file format.");
            }
            else {
                parseRule = file.readLine();
                title = file.readLine();
            }
            if(title == null || !title.equals(Headers[3])){
                throw new ParsingException("ERROR: Problem with file format.");
            }
            else {
                queryText = file.readLine();
            }
        } catch (ParsingException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        catch (IOException err){
            System.err.println("ERROR: problem with reading the file");
            System.exit(1);
        }
    }

    public String getCorpusPath(){
        return corpusPath;
    }

    public String getIndexerCondition() {
        return indexerCondition;
    }

    public String getParseRule() {
        return parseRule;
    }

    public String getQueryText() {
        return queryText;
    }
}
