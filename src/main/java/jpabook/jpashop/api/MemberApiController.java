package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController //@Controller @ResponseBody
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){

        Member member=new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("/signup")
    public CreateMemberResponse saveMemberV3(@RequestBody @Valid CreateMemberRequest request){

        Member member=new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @GetMapping("/api/v2/members")
    public Result memberV2(){
        List<Member> members = memberService.findMembers();

        List<MemberDto> collect=members.stream()
                .map(member -> new MemberDto(member.getName()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    //회원 등록
    @Data
    static class CreateMemberRequest{
        private String name;
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    //회원 조회
    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;
    }
}
