package processing.textStructure;

/**
 * Wrapper class for a single word containing relevant attributes for distance calculation and string extraction from
 * the containing block or file.
 */
public class Word {
	private final Block srcBlk;             //A reference for the contaning Block object.
	private final long srcBlkOffset;       //The offset of the word within the block
	private final int length;               //Length of the word

	private final int wordHash;             //Hash of the word - for quick dictionary querying without
												//unnecessary extraction and conversion.

	/**
	 * The constructor.
	 *
	 * @param source   The Block where this word resides.
	 * @param startIdx The offset within the block where the word starts.
	 * @param endIdx   The  offset within the block where the word ends.
	 */
	public Word(Block source, long startIdx, long endIdx) {
		this.srcBlk = source;
		this.srcBlkOffset = startIdx;
		this.length = (int) (endIdx - startIdx);
		this.wordHash = extractWord().hashCode();
	}

	/**
	 * Simple getter
	 *
	 * @return The source block
	 */
	public Block getSrcBlk() {
		return this.srcBlk;
	}

	/**
	 * Get the actual string of the word from within the block.
	 *
	 * @return The word in String format.
	 */
	protected String extractWord() {
		//TODO implement me!!!
	}

	/**
	 * Get the hashCode of the word
	 *
	 * @return The wordHash.
	 */
	public int getHash() {
		return this.wordHash;
	}

	/**
	 * The source block offset within the file + the offset of the word within the block = offset within an entry!
	 *
	 * @return offset within the entire FILE where the word resides.
	 */
	public long getEntryIndex() {
		return this.srcBlk.getStartIndex() + this.srcBlkOffset;
	}
}
