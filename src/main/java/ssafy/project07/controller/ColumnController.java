// 📦 백엔드 추가: ColumnController.java
// GPT 코드임 -> 수정 필요할 수도 있음
package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ssafy.project07.domain.User;
import ssafy.project07.dto.column.ColumnRequest;
import ssafy.project07.dto.column.ColumnResponse;
import ssafy.project07.service.ColumnService;

import java.util.List;

@RestController
@RequestMapping("/api/columns")
@RequiredArgsConstructor
public class ColumnController {

    private final ColumnService columnService;

    // 💊 칼럼 등록
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody ColumnRequest request,
                                       @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(columnService.create(request, user));
    }

    // ✍ 칼럼 상세
    @GetMapping("/{id}")
    public ResponseEntity<ColumnResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(columnService.getColumnById(id));
    }

    // 📋 약사별 칼럼 목록 조회
    @GetMapping("/pharmacists/{id}")
    public ResponseEntity<List<ColumnResponse>> getByPharmacist(@PathVariable Long id) {
        return ResponseEntity.ok(columnService.getColumnsByPharmacist(id));
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody ColumnRequest request,
                                       @AuthenticationPrincipal User user) {
        columnService.update(id, request, user);
        return ResponseEntity.ok().build();
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       @AuthenticationPrincipal User user) {
        columnService.delete(id, user);
        return ResponseEntity.ok().build();
    }

    // 전체 칼럼 리스트 보기
    @GetMapping("/all")
    public ResponseEntity<List<ColumnResponse>> getAll() {
        return ResponseEntity.ok(columnService.getAllColumns());
    }

}
