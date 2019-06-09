package dataStructures;

import processing.parsingRules.IparsingRule;
import processing.searchStrategies.IsearchStrategy;
import processing.textStructure.Corpus;
import utils.WrongMD5ChecksumException;

import java.io.FileNotFoundException;

/**
 * The abstract class describing the necessary methods and common implementations of all indexing data structures.
 * @param <T>   The search stratagy used by this indexing engine. Can be any class that implements the
 *                  IsearchStrategy interface.
 */
public abstract class Aindexer<T extends IsearchStrategy> {

	public static enum IndexTypes {DICT, NAIVE, NAIVE_RK, SUFFIX_TREE, CUSTOM}
    IndexTypes dataStructType;
    protected Corpus origin;


	/**
	 * Basic constructor accepting origin Corpus
	 * @param origin    The corpus indexed in this DS.
	 */
	public Aindexer(Corpus origin){
        this.origin = origin;
    }

	/**
	 * Main indexing method. Common implementation trying to read indexed cache file
	 * This method must:
	 * 1) Try and reada a cached index file if one exists, and if so - populate the entire span of the program from the serialized data
	 * 2) If a cache file does not exist, or if the checksum stored in the file does not match the checksum of the current corpus,
	 *    you must reindex the corpus and then store the cache file to disk (replacing any older file that may exist).
	 */
	public void index() {
		try {
			readIndexedFile();
		} catch (FileNotFoundException | WrongMD5ChecksumException e) {
			indexCorpus();
			writeIndexFile();
		}
    }

	/**
	 * get the backing search interface.
	 * @return  The search interface implementation used by this indexer.
	 */
	public abstract T asSearchInterface();

	/**
	 * Index the Corpus internally using the parsing rules defined for this datastructure.
	 */
    protected abstract void indexCorpus();

	/**
	 * Try to read a cached index file if one already exists.
	 * NOTE! you may make this method not abstract if you wish.
	 */
	protected abstract void readIndexedFile() throws FileNotFoundException, WrongMD5ChecksumException;


	/**
	 * Getter for the cached index file.
	 * @return  the path to the cached index file.
	 */
	private String getIndexedPath() {
		//TODO implement me!!!
	}

	/**
	 * Write the indernal index into file.
	 * NOTE! you may make this method not abstract if you wish.
	 */
	protected abstract void writeIndexFile();

	/**
	 * Extract the parsing rule used for indexing this data structure.
	 * @return  an instance of a parser implementing IparsingRule
	 */
	public abstract IparsingRule getParseRule();

	/**
	 * simple getter
	 * @return  the DS type
	 */
    protected abstract IndexTypes getIndexType();

	/**
	 * simple getter
	 * @return  Regerence to the origin Corpus
	 */
	public Corpus getCorpus(){return  this.origin;}
}
