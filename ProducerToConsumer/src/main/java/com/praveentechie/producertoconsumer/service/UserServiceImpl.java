package com.praveentechie.producertoconsumer.service;

import com.praveentechie.producertoconsumer.dto.UserDto;
import com.praveentechie.producertoconsumer.dto.UserMapper;
import com.praveentechie.producertoconsumer.model.Role;
import com.praveentechie.producertoconsumer.model.User;
import com.praveentechie.producertoconsumer.repository.RoleRepository;
import com.praveentechie.producertoconsumer.repository.UserRepository;
import com.praveentechie.producertoconsumer.utils.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;


    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<UserDto> findAllUsers() {

        List<User> users=userRepository.findAll();

        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user=userRepository.findByEmail(email).get();
        if(user==null)
            return null;
        return userMapper.toDto(user);
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user=userMapper.toEntity(userDto);
        List<Role> roles=new ArrayList<>();
        for(String roleName:userDto.getRoles())
        {
            Role role=roleRepository.findByName(roleName);
            if(role==null){
                role= Role.builder().name(roleName).build();
                roleRepository.save(role);
            }
            roles.add(role);
        }
        user.setRoles(roles);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto findUserByName(String userName) {
        User user= userRepository.findByEmail(userName).get();
        if(user!=null)
            return userMapper.toDto(user);
        else
            return null;

    }




	/* private void sendPasswordEmail(String recipientEmail, String password) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(recipientEmail);
    message.setSubject("Password Reset");
    message.setText("Your new password is: " + password);

    javaMailSender.send(message);
} */

/*@PostMapping("/forgot-password")
public ResponseEntity<Map<String, String>> forgotPassword(@RequestParam("email") String email) {
    // Check if the email exists in the database
    UserDto user = userService.findUserByEmail(email);

    if (user == null) {
        // User not found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "User not found"));
    }

    String password = user.getPassword(); // Get the existing password from the user object

    // Send the password to the user's email
    sendPasswordEmail(user.getEmail(), password);

    // Return a success message
    return ResponseEntity.ok(Collections.singletonMap("message", "Password sent to your email."));
} */


    @Override
    public boolean forgotPwd(String email) {
        // check Record Presence in db with given mail
        User entity = userRepository.findByEmail(email).get();

        // if record not available sent msg
        if (entity == null){
            return false;
        }

        // if record available in db sent password and sent success msg
        String subject = "<h1>Recover Password<h1>";
        String body = "Your Pwd :: " + entity.getPassword();

        emailUtil.sendEmail(email, subject, body);

        return true;

    }
}
