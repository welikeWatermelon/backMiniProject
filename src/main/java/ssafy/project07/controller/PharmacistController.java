package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ssafy.project07.domain.User;
import ssafy.project07.domain.enums.UserRole;
import ssafy.project07.dto.column.ColumnRequest;
import ssafy.project07.dto.column.ColumnResponse;
import ssafy.project07.dto.pharmacist.PharmacistResponse;
import ssafy.project07.service.ColumnService;
import ssafy.project07.service.PharmacistService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pharmacists")
public class PharmacistController {

    private final PharmacistService pharmacistService;
    private final ColumnService columnService;

    // 약사 모두 보기 OK
    // 프론트에서 Id는 숨겨야겠지
    @GetMapping
    public ResponseEntity<List<PharmacistResponse>> getAllPharmacists() {
        return ResponseEntity.ok(pharmacistService.getAllPharmacists());
    }

    // 특정 id의 칼럼 목록 보기
    @GetMapping("/{id}/columns")
    public ResponseEntity<List<ColumnResponse>> getColumnsByPharmacist(@PathVariable Long id) {
        return ResponseEntity.ok(columnService.getColumnsByPharmacist(id));
    }

    // 특정 id의 칼럼 상세 보기
    @GetMapping("/{id}/columns/{columnId}")
    public ResponseEntity<ColumnResponse> getColumnDetail(@PathVariable Long id) {
        return ResponseEntity.ok(columnService.getColumnById(id));
    }

    // 칼럼 등록하기 -> 약사만 할 수 있도록 해야함 -> 이걸 약사만 하도록 어케하냐?
    @PostMapping("/{id}/columns/register")
    public ResponseEntity<Long> createColumn(@RequestBody ColumnRequest request,
                                             @AuthenticationPrincipal User user) {
        if (user.getRole() != UserRole.PHARMACIST) {
            throw new IllegalStateException("칼럼은 약사만 등록할 수 있습니다.");
        }

        // 칼럼 등록
        return ResponseEntity.ok(columnService.create(request, user));
    }

    // 칼럼 수정하기 -> 약사 + 작성자
    @PutMapping("/columns/{id}")
    public ResponseEntity<Void> updateColumn(@PathVariable Long id,
                                             @RequestBody ColumnRequest request,
                                             @AuthenticationPrincipal User user) {
        if (user.getRole() != UserRole.PHARMACIST) {
            throw new IllegalStateException("해당 약사만 칼럼을 수정할 수 있습니다.");
        }
        columnService.update(id, request,user);
        return ResponseEntity.ok().build();
    }

    // 칼럼 삭제하기 -> 약사 + 작성자
    @DeleteMapping("/columns/{id}")
    public ResponseEntity<Void> deleteColumn(@PathVariable Long id,
                                             @AuthenticationPrincipal User user) {
        columnService.delete(id,user);
        return ResponseEntity.ok().build();
    }
}
