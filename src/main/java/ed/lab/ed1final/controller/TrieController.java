package ed.lab.ed1final.controller;

import ed.lab.ed1final.trie.Trie;
import ed.lab.ed1final.trie.TrieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trie")
public class TrieController {

    private final Trie trie;

    @Autowired
    public TrieController(Trie trie) {
        this.trie = trie;
    }

    private boolean isValid(String word) {
        return StringUtils.hasText(word) && word.matches("[a-z]+");
    }

    @PostMapping("/{word}")
    public ResponseEntity<?> insertWord(@PathVariable String word) {
        if (!isValid(word)) {
            return ResponseEntity.badRequest().build();
        }
        trie.insert(word);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{word}/count")
    public ResponseEntity<?> countWordsEqualTo(@PathVariable String word) {
        TrieResponse response = new TrieResponse();
        response.setWord(word);
        response.setWordsEqualTo(trie.countWordsEqualTo(word));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{prefix}/prefix")
    public ResponseEntity<?> countWordsStartingWith(@PathVariable String prefix) {
        TrieResponse response = new TrieResponse();
        response.setPrefix(prefix);
        response.setWordsStartingWith(trie.countWordsStartingWith(prefix));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{word}")
    public ResponseEntity<?> deleteWord(@PathVariable String word) {
        if (!isValid(word) || trie.countWordsEqualTo(word) == 0) {
            return ResponseEntity.badRequest().build();
        }
        trie.erase(word);
        return ResponseEntity.noContent().build();
    }
}