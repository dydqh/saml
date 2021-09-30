package com.datasolution.saml_idp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class IdpResponseVo {
	
	private String id;
	private String password;
	private String SAMLResponse;
	
}
