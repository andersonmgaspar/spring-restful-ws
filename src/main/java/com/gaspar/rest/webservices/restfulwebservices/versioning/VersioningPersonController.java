package com.gaspar.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	@GetMapping("/v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Bob Marley");
	}

	@GetMapping("/v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Anderson", "Gaspar"));
	}

	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getPersonV1RequestParameter() {
		return new PersonV1("Bob Dylan");
	}

	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getPersonV2RequestParameter() {
		return new PersonV2(new Name("Mr.", "Anderson"));
	}
	
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getPersonV1Header() {
		return new PersonV1("Bob");
	}

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getPersonV2Header() {
		return new PersonV2(new Name("Johnny", "Bravo"));
	}
	
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getPersonV1AcceptHeader() {
		return new PersonV1("Bob Dylan 11");
	}
	
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getPersonV2AcceptHeader() {
		return new PersonV2(new Name("Johnny", "Bravo"));
	}
	
}
