package com.akahori.auth.service;

import com.akahori.auth.model.AccountModel;
import com.akahori.auth.model.UserModel;
import com.akahori.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) throw new UsernameNotFoundException("");

        UserModel userModel = userRepository.selectByUser(username);

        // ユーザが存在しない場合
        if (userModel == null) throw new UsernameNotFoundException("");
        // アカウントの有効期限切れ、アカウントのロック、パスワードの有効期限切れ、ユーザの無効を判定
        if (!userModel.isAccountNonExpired() || !userModel.isAccountNonLocked() ||
                !userModel.isCredentialsNonExpired() || !userModel.isEnabled())
            throw new UsernameNotFoundException("");
        return userModel;
    }

    public UserModel create(AccountModel model) {
        String password = passwordEncoder.encode(model.getPassword());
        UserModel userModel = new UserModel(model.getUsername(), model.getUsername(), password, true);
        userRepository.save(userModel);

        return userModel;
    }
}
