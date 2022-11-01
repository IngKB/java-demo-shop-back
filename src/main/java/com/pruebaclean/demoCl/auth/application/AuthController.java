package com.pruebaclean.demoCl.auth.application;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.pruebaclean.demoCl.auth.domain.useCases.signup.SignupBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebaclean.demoCl.auth.domain.useCases.login.LoginRequestModel;
import com.pruebaclean.demoCl.auth.domain.useCases.signup.SignupRequestModel;
import com.pruebaclean.demoCl.auth.domain.useCases.login.LoginResponseModel;
import com.pruebaclean.demoCl.auth.domain.useCases.signup.SignupResponseModel;
import com.pruebaclean.demoCl.auth.infraestructure.security.jwt.JwtUtils;
import com.pruebaclean.demoCl.auth.infraestructure.security.services.UserDetailsImplModel;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtUtils jwtUtils;

  private final SignupBoundary signupBoundary;

  public AuthController(SignupBoundary signupBoundary) {
    this.signupBoundary = signupBoundary;
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestModel loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImplModel userDetails = (UserDetailsImplModel) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new LoginResponseModel(jwt,
                         userDetails.getId(), 
                         userDetails.getUsername(),
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestModel signUpRequest) {
    SignupResponseModel signupResponse = signupBoundary.signup(signUpRequest);
    if (signupResponse.getCode() == 1) {
      return ResponseEntity
          .badRequest()
          .body(signupResponse.getMessage());
    }
    return ResponseEntity.ok(signupResponse.getMessage());
  }
}
