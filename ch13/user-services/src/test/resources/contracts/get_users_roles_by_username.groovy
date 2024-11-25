package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return the roles for username =admin@wxauction"

    request {
        url "/v1/users/admin@wxauction.com/roles"
        method GET()
        headers {
            header("traceparent", "ABC")
        }
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(
                roles: ["ROLE_ADMIN", "ROLE_USER"]
        )
    }
}
