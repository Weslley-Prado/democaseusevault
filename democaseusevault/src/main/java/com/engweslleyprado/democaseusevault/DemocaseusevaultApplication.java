package com.engweslleyprado.democaseusevault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;

@SpringBootApplication
public class DemocaseusevaultApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemocaseusevaultApplication.class, args);

		VaultTemplate vaultTemplate = new VaultTemplate(new VaultEndpoint(),
				new TokenAuthentication("00000000-0000-0000-0000-000000000000"));

		Secrets secrets = new Secrets();
		secrets.username = "hello";
		secrets.password = "world";

		vaultTemplate.write("secret/myapp", secrets);

		VaultResponseSupport<Secrets> response = vaultTemplate.read("secret/myapp", Secrets.class);
		System.out.println(response.getData().getUsername());

		vaultTemplate.delete("secret/myapp");
	}
	}

}
