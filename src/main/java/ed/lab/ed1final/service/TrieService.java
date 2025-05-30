package ed.lab.ed1final.service;

import ed.lab.ed1final.trie.Trie;
import org.springframework.stereotype.Service;

@Service
public class TrieService {

    private final Trie trie;

    public TrieService(Trie trie) {
        this.trie = trie;
    }

    public void insertWord(String word) {
        trie.insert(word);
    }

    public int countWordsEqualTo(String word) {
        return trie.countWordsEqualTo(word);
    }

    public int countWordsStartingWith(String prefix) {
        return trie.countWordsStartingWith(prefix);
    }

    public void eraseWord(String word) {
        trie.erase(word);
    }
}
