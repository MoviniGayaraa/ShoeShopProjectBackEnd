package lk.ijse.gdse.Controller;

import lk.ijse.gdse.reqAndresp.response.JwtAuthResponse;
import lk.ijse.gdse.reqAndresp.secure.SignIn;
import lk.ijse.gdse.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final AuthenticationService authenticationService;

    @GetMapping("/health")
    public String healthCheck(){
        return "----------@DONE@-----------";
    }

    //singUp - Only one time
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthResponse> signUp() {
        return ResponseEntity.ok(authenticationService.signUp());
    }

    //signIn
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignIn signInReq) {
        return ResponseEntity.ok(authenticationService.signIn(signInReq));
    }
}
