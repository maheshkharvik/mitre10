package com.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.data.UserAccountData;
import com.example.data.UserAccountContactResponse;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.*;
import org.springframework.core.*;
import org.slf4j.*;


@Component
public class CallRestService {
	
	Logger logger = LoggerFactory.getLogger(CallRestService.class);

	public UserAccountContactResponse getUserAccountForId(final int userId) {
		UserAccountContactResponse userAccountContactResponse = new UserAccountContactResponse();
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<List<UserAccountData>> userAccountResponse = restTemplate.exchange(
					"https://jsonplaceholder.typicode.com/users", HttpMethod.GET, null,
					new ParameterizedTypeReference<List<UserAccountData>>() {
					});

			List<UserAccountData> userAccounts = userAccountResponse.getBody();

			for (UserAccountData userAccount : userAccounts) {
				if (userAccount.getId() == userId) {
					userAccountContactResponse.setEmail(userAccount.getEmail());
					userAccountContactResponse.setId(userAccount.getId());
					userAccountContactResponse.setPhone(userAccount.getPhone());

				}
			}
			if (0 == userAccountContactResponse.getId()) {
				userAccountContactResponse.setId(-1);
			}

			return userAccountContactResponse;

		}

		catch (HttpStatusCodeException exception) {
			int statusCode = exception.getStatusCode().value();
			logger.debug("Exception connecting to the service" + statusCode);
		}
		return null;
	}
}



