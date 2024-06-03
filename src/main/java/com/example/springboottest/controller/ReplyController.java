package com.example.springboottest.controller;

import com.example.springboottest.dto.PageRequestDTO;
import com.example.springboottest.dto.PageResponseDTO;
import com.example.springboottest.dto.ReplyDTO;
import com.example.springboottest.service.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor // 생성자 주입
public class ReplyController {
    private final ReplyService replyService;

    // 등록, 수정의 경우 값을 받아와야 하기때문에 MediaType, @RequestBody를 사용
    @Operation(summary = "Replies Post", description = "POST 방식으로 댓글 등록") // Operation으로 설명 추가
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE) // consumes로 JSON 형식만 받음
    public Map<String, Long> register(@RequestBody ReplyDTO replyDTO,
                                      BindingResult bindingResult) throws BindException { // RequestBody로 replyDTO를 받음
        log.info("ReplyDTO: {}", replyDTO);

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        Map<String, Long> map = new HashMap<>();
        Long rno = replyService.register(replyDTO);
        map.put("rno", rno);

        return map;
    }

    @Operation(summary = "Replies of Board", description = "GET 방식으로 댓글 조회") // Operation으로 설명 추가
    @GetMapping(value = "/list/{bno}")  // GET 방식으로 댓글 조회, PathVariable라는 어노테이션을 이용해 호출경로의 값을 직접 파라미터의 변수로 처리
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("bno") Long bno, PageRequestDTO pageRequestDTO) {
        PageResponseDTO<ReplyDTO> responseDTO = replyService.getListOfBoard(bno, pageRequestDTO);
        return responseDTO;
    }

    @Operation(summary = "Read Reply", description = "GET 방식으로 댓글 조회") // Operation으로 설명 추가
    @GetMapping("/{rno}")
    public ReplyDTO getReplyDTO(@PathVariable("rno") Long rno) {
        ReplyDTO replyDTO = replyService.read(rno);
        return replyDTO;
    }

    @Operation(summary = "Delete Reply", description = "DELETE 방식으로 댓글 삭제")
    @DeleteMapping("/{rno}") // DELETE 방식으로 댓글 삭제
    public Map<String, Long> remove(@PathVariable("rno") Long rno) { // PathVariable로 rno를 받아서 삭제
        replyService.remove(rno);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);
        return resultMap;
    }

    @Operation(summary = "Modify Reply", description = "PUT 방식으로 댓글 수정")
    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE) // consumes로 JSON 형식만 받음
    public Map<String, Long> modify(@PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO) {
        replyDTO.setRno(rno); // rno 값을 일치시킴
        replyService.modify(replyDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);
        return resultMap;
    }

    @Operation(summary = "Modify Writer", description = "PUT 방식으로 댓글 작성자 수정")
    @PutMapping(value = "/writer/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE) // consumes로 JSON 형식만 받음
    public Map<String, Long> modifyWriter(@PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO) {
        replyDTO.setRno(rno); // rno 값을 일치시킴
        replyService.modifyWriter(replyDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);
        return resultMap;
    }

}
