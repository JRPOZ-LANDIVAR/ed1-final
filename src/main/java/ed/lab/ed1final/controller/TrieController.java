package ed.lab.ed1final.controller;

import ed.lab.ed1final.service.TrieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/trie")
public class TrieController {

    private final TrieService trieService;

    public TrieController(TrieService trieService) {
        this.trieService = trieService;
    }

    // POST /trie/{word} - Insertar palabra
    @PostMapping("/{word}")
    public ResponseEntity<Void> insertWord(@PathVariable String word) {
        if (!isValidWord(word)) {
            return ResponseEntity.badRequest().build();
        }
        trieService.insertWord(word);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // GET /trie/{word}/count - Obtener cantidad de ocurrencias exactas
    @GetMapping("/{word}/count")
    public ResponseEntity<Map<String, Object>> countWordsEqualTo(@PathVariable String word) {
        if (!isValidWord(word)) {
            return ResponseEntity.badRequest().build();
        }
        int count = trieService.countWordsEqualTo(word);
        return ResponseEntity.ok(Map.of(
                "word", word,
                "wordsEqualTo", count
        ));
    }

    // GET /trie/{prefix}/prefix - Obtener cantidad de palabras que empiezan con el prefijo
    @GetMapping("/{prefix}/prefix")
    public ResponseEntity<Map<String, Object>> countWordsStartingWith(@PathVariable String prefix) {
        if (!isValidWord(prefix)) {
            return ResponseEntity.badRequest().build();
        }
        int count = trieService.countWordsStartingWith(prefix);
        return ResponseEntity.ok(Map.of(
                "word", prefix,
                "wordsStartingWith", count
        ));
    }

    // DELETE /trie/{word} - Eliminar una ocurrencia de la palabra
    @DeleteMapping("/{word}")
    public ResponseEntity<Void> eraseWord(@PathVariable String word) {
        if (!isValidWord(word)) {
            return ResponseEntity.badRequest().build();
        }
        trieService.eraseWord(word);
        return ResponseEntity.noContent().build();
    }

    private boolean isValidWord(String word) {
        return word != null && !word.isEmpty() && word.matches("[a-z]+");
    }
}
