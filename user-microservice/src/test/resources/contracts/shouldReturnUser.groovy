package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/uaa/v1/me'
        headers {
            contentType('application/json')
        }
    }
    response {
        status OK()
        body([
                username    : value(producer(regex('[A-Za-z0-9]+'))),
                firstName   : value(producer(regex('[A-Za-z]+'))),
                lastName    : value(producer(regex('[A-Za-z]+'))),
                email       : value(producer(regex('[A-Za-z0-9]+\\@[A-Za-z]+\\.[A-Za-z]+'))),
                createdAt   : value(producer(regex('[0-9]+'))),
                lastModified: value(producer(regex('[0-9]+'))),
                id          : value(producer(regex('[0-9]+')))
        ])
        headers {
            header('Content-Type' : value(
                    producer('application/json;charset=UTF-8'),
                    consumer('application/json;charset=UTF-8')
            ))
        }
    }
}
