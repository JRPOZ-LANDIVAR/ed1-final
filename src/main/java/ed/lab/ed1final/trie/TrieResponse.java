package ed.lab.ed1final.trie;

public class TrieResponse {
    private String word;
    private String prefix;
    private Integer wordsEqualTo;
    private Integer wordsStartingWith;


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Integer getWordsEqualTo() {
        return wordsEqualTo;
    }

    public void setWordsEqualTo(Integer wordsEqualTo) {
        this.wordsEqualTo = wordsEqualTo;
    }

    public Integer getWordsStartingWith() {
        return wordsStartingWith;
    }

    public void setWordsStartingWith(Integer wordsStartingWith) {
        this.wordsStartingWith = wordsStartingWith;
    }
}