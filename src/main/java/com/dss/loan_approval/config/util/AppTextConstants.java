package com.dss.loan_approval.config.util;

import org.springframework.security.core.parameters.P;

public class AppTextConstants {
    private AppTextConstants() {
    }

    public static final String USERNAME_ALREADY_EXISTS = "email already exist";
    public static final String SIGNUP_SUCCESSFUL = "Sign up successful";
    public static final String INVALID_ROLE = "Invalid role specified";
    public static final String PROFILE_SUBMITTED_SUCCESSFULLY = "Profile submitted successfully";

    public static final String FAILED_TO_SUBMIT_PROFILE = "Failed to submit profile";
    public static final String ERROR_SAVING_PROFILE = "Error saving customer profile";
    public static final String PROFILE_NOTFOUND = "Profile not found";

    public static final String PROFILE_FETCHED_SUCCESSFULLY = "Profile successfully fetched";
    public static final String ERROR_FETCHING_PROFILE = "Error fetching profile";
    public static final String FAILED_TO_FETCH_PROFILE = "Failed to fetched profile";

    public static final String EMPLOYMENT_DETAILS_SUBMITTED_SUCCESSFULLY = "Employment details submitted successfully";
    public static final String ERROR_SAVING_EMPLOYMENT_DETAILS = "Error saving employment details";
    public static final String FAILED_TO_SUBMIT_EMPLOYMENT_DETAILS = "Failed to submit employment details";

    public static final String EMPLOYMENT_DETAILS_NOT_FOUND = "Employment details not found";
    public static final String ERROR_FETCHING_EMPLOYMENT_DETAILS = "Error fetching employment details";
    public static final String EMPLOYMENT_DETAILS_FETCHED_SUCCESSFULLY = "Employment details fetched successfully";

    public static final String FAILED_TO_FETCH_EMPLOYMENT_DETAILS = "Failed to fetch employment details";
    public static final String ERROR_SAVING_PROMOTION_DETAILS = "Error saving promotional details";
    public static final String PROMOTION_DETAILS_SUBMITTED_SUCCESSFULLY = "Promotional details submitted successfully";

    public static final String FAILED_TO_SUBMIT_PROMOTION_DETAILS = "Failed to submit promotion details";
    public static final String PROMOTION_DETAILS_NOT_FOUND = "Promotion details not found";
    public static final String PROMOTION_DETAILS_FETCHED_SUCCESSFULLY = "Promotion details fetch successfully";

    public static final String ERROR_FETCHING_PROMOTION_DETAILS = "Error fetching promotion details";
    public static final String FAILED_TO_FETCH_PROMOTION_DETAILS = "Failed to fetched promotion details";
    public static final String LOAN_DETAILS_SUBMITTED_SUCCESSFULLY = "Loan details successfully submitted";

    public static final String FAILED_TO_SUBMIT_LOAN_DETAILS = "Failed to submit loan details";
    public static final String ERROR_SAVING_LOAN_DETAILS = "Error saving loan details";
    public static final String LOAN_DETAILS_NOT_FOUND = "Loan details not found";

    public static final String LOAN_DETAILS_FETCHED_SUCCESSFULLY = "Loan details successfully fetched";
    public static final String ERROR_FETCHING_LOAN_DETAILS = "Error fetching loan details";
    public static final String FAILED_TO_FETCH_LOAN_DETAILS = "Failed to fetch loan";

    public static final String PROFILE_UPDATED_SUCCESSFULLY = "Profile updated successfully";
    public static final String FAILED_TO_UPDATE_PROFILE = "Failed to update profile";
    public static final String ERROR_UPDATING_PROFILE = "Error updating profile";

    public static final String EMPLOYMENT_DETAILS_UPDATED_SUCCESSFULLY = "Employment details updated successfully";
    public static final String ERROR_UPDATING_EMPLOYMENT_DETAILS = "Error updating employment details";
    public static final String FAILED_TO_UPDATE_EMPLOYMENT_DETAILS = "Failed to update employment details";

    public static final String LOAN_DETAILS_UPDATED_SUCCESSFULLY = "Loan details updated successfully";
    public static final String ERROR_UPDATING_LOAN_DETAILS = "Error updating loan details";
    public static final String FAILED_TO_UPDATE_LOAN_DETAILS = "Failed to update loan details";

    public static final String PROMOTION_DETAILS_UPDATED_SUCCESSFULLY = "Promotion details updated successfully";
    public static final String ERROR_UPDATING_PROMOTION_DETAILS = "Error updating promotion detials";
    public static final String FAILED_TO_UPDATE_PROMOTION_DETAILS = "Failed to update promotion details";

    public static final String ALL_PROFILE_RETRIVED_SUCCESSFULLY = "All profiles retrieved successfully";
    public static final String ERROR_FETCHING_ALL_PROFILE = "Error fetching all profiles";
    public static final String FAILED_TO_FETCH_ALL_PROFILE = "Failed to fetch profiles";

    public static final String COMMENT_SUBMITTED_SUCCESSFULLY = "Verification comment submitted successfully";
    public static final String ERROR_SUBMITTING_COMMENT = "Error occurred while submitting verification comment";
    public static final String FAILED_TO_SUBMIT_COMMENT = "Failed to submit verification comment";

    public static final String PROFILE_FORWARDED_SUCCESSFULLY = "Profile forwarded to compliance successfully";
    public static final String FAILED_TO_FORWARD_PROFILE = "Failed to forward profile";
    public static final String ERROR_FORWARDING_PROFILE = "Error occurred while forwarding profile";

    public static final String DATA_FETCHED_SUCCESSFULLY = "Data fetched successfully";
    public static final String ERROR_FETCHING_DATA = "Error fetching data";
    public static final String FAILED_TO_FETCH_DATA = "Failed to fetch data";

    public static final String PROFILE_NOT_FOUND = "Customer profile not found";
    public static final String COMPLIANCE_COMMENT_SUBMITTED_SUCCESSFULLY = "Compliance comment submitted successfully";
    public static final String FAILED_TO_SUBMIT_COMPLIANCE_COMMENT = "Failed to submit compliance comment";
    public static final String ERROR_SUBMITTING_COMPLIANCE_COMMENT = "Error submitting compliance comment";

    public static final String COMPLIANCE_APPROVED_SUCCESSFULLY = "Customer profile approved by compliance desk";
    public static final String FAILED_TO_APPROVE_COMPLIANCE = "Failed to approve customer profile at compliance desk";
    public static final String ERROR_APPROVING_COMPLIANCE = "Error approving customer profile at compliance desk";

    public static final String COMPLIANCE_DECLINED_SUCCESSFULLY = "Customer profile declined by compliance desk";
    public static final String FAILED_TO_DECLINE_COMPLIANCE = "Failed to decline customer profile at compliance desk";
    public static final String ERROR_DECLINING_COMPLIANCE = "Error declining customer profile at compliance desk";

    public static final String MD_COMMENT_SUBMITTED_SUCCESSFULLY = "MD comment submitted successfully";
    public static final String FAILED_TO_SUBMIT_MD_COMMENT = "Failed to submit MD comment";
    public static final String ERROR_SUBMITTING_MD_COMMENT = "Error submitting MD comment";

    public static final String MD_APPROVED_SUCCESSFULLY = "Customer profile approved by MD desk";
    public static final String FAILED_TO_APPROVE_MD = "Failed to approve customer profile at MD desk";
    public static final String ERROR_APPROVING_MD = "Error approving customer profile at MD desk";

    public static final String MD_DECLINED_SUCCESSFULLY = "Customer profile declined by MD desk";
    public static final String FAILED_TO_DECLINE_MD = "Failed to decline customer profile at MD desk";
    public static final String ERROR_DECLINING_MD = "Error declining customer profile at MD desk";

    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    public static final String LOGOUT_SUCCESSFULLY = "User logged out";
    public static final String FAILED_TO_UPDATE_OFFICER = "Failed to update officer profile";

    public static final String REGISTRATION_SUCCESSFUL = "Officer registered successfully";
    public static final String ERROR_REGISTERING_OFFICER = "Error registering officer";
    public static final String FAILED_TO_REGISTER_OFFICER = "Failed to register officer";

    public static final String OFFICER_NOT_FOUND = "Officer not found";
    public static final String OFFICER_NOT_VERIFIED = "Officer not verified by MD";
    public static final String INVALID_CREDENTIALS = "Invalid email or password";

    public static final String LOGIN_SUCCESSFUL = "Login successful";
    public static final String ERROR_LOGGING_IN = "Error logging in officer";
    public static final String FAILED_TO_LOGIN = "Failed to login officer";

    public static final String MD_REGISTERATION_SUCCESSFUL = "MD registered successfully";
    public static final String MD_ALREADY_REGISTERED = "Managing Director already registered";
    public static final String OFFICER_PROFILE_UPDATED_SUCCESSFULLY = "Officer profile updated successfully";

    public static final String FAILED_TO_SUBMIT_CREDIT_ADMIN_COMMENT = "Failed to submit Credit Admin comment";
    public static final String ERROR_SAVING_CREDIT_ADMIN_COMMENT = "Error saving Credit Admin comment";
    public static final String CREDIT_ADMIN_COMMENTS_FETCHED_SUCCESSFULLY = "Credit Admin comments fetched successfully";

    public static final String FAILED_TO_FETCH_CREDIT_ADMIN_COMMENTS = "Failed to fetch Credit Admin comments";
    public static final String OFFICER_FETCHED_SUCCESSFULLY = "Officers fetched successfully";
    public static final String ERROR_FETCHING_OFFICERS = "Error fetching officers";

    public static final String FAILED_TO_FETCHED_OFFICER = "Failed to fetch officers";
    public static final String ERROR_FETCHING_OFFICERBYID = "Error fetching officer by id";
    public static final String OFFICER_NOTFOUND = "Officer not found";

    public static final String OFFICER_APPROVED_SUCCESSFULLY = "Officer approved successfully";
    public static final String PENDING_OFFICER_RETRIEVED = "Pending officer retrieved";
    public static final String ERROR_FETCHING_UPDATE_OFFICERS = "Error updating officer profile";

    public static final String OFFICERS_FETCHED_SUCCESSFULLY = "All officers fetched successfully";
    public static final String STAFF_ENABLED_SUCCESSFULLY = "Staff account enabled successfully";
    public static final String FAILED_TO_ENABLE_OFFICER = "Failed to enable staff account";

    public static final String ERROR_ENABLING_OFFICER = "Error enabling staff account";
    public static final String STAFF_DISABLED_SUCCESSFULLY = "Staff account disabled successfully";
    public static final String FAILED_TO_DISABLE_OFFICER = "Failed to disable staff account";

    public static final String ERROR_DISABLING_OFFICER = "Error disabling staff account";
    public static final String STAFF_DELETED_SUCCESSFULLY = "Staff account deleted successfully";
    public static final String FAILED_TO_DELETE_OFFICER = "Failed to delete staff account";

    public static final String RESET_CODE_GENERATED_SUCCESSFULLY = "Reset code generated successfully";
    public static final String FAILED_TO_GENERATE_RESET_CODE = "Failed to generate reset code";
    public static final String ERROR_GENERATING_RESET_CODE = "Error generating reset code";

    public static final String INVALID_RESET_CODE = "Invalid reset code";
    public static final String PASSWORD_RESET_SUCCESSFULLY = "Password reset successfully";
    public static final String FAILED_TO_RESET_PASSWORD = "Failed to reset password";

    public static final String ERROR_RESETTING_PASSWORD = "Error resetting password";
    public static final String ERROR_DELETING_OFFICER = "Error deleting staff account";
    public static final String RESET_CODE_SENT_TO_EMAIL = "Reset code sent to your email";


    public static final String PASSWORD_UPDATED_SUCCESSFULLY = "Password updated successfully";
    public static final String OFFICER_REGISTERED_SUCCESSFULLY = "Officer registered successfully";
    public static final String MD_REGISTERED_SUCCESSFULLY = "Managing Director registered successfully";

    public static final String OFFICER_PROFILE_FETCHED_SUCCESSFULLY = "Officer profile fetched successfully";

}


