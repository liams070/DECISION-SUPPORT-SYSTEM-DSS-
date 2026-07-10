package com.dss.loan_approval.modules.account.service;

import com.dss.loan_approval.config.enums.Role;
import com.dss.loan_approval.config.exceptions.AppException;
import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.SignupRequestDTO;
import com.dss.loan_approval.modules.account.dto.response.SignupResponseDTO;
import com.dss.loan_approval.modules.model.entity.User;
import com.dss.loan_approval.modules.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.dss.loan_approval.config.util.AppCodeConstants.*;
import static com.dss.loan_approval.config.util.AppTextConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignupService {

}
