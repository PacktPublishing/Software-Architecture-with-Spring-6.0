package com.packtpub.userservices.internal.usecases;

import com.packtpub.userservices.internal.entity.Role;
import com.packtpub.userservices.internal.entity.User;
import com.packtpub.userservices.internal.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetUserRolesUseCaseTest {

    @Test
    void givenUserExists_whenExecute_thenReturnsRoles() {

        UserRepository mockRepository = mock();
        GetUserRolesUseCase useCase = new GetUserRolesUseCase(mockRepository);

        Set<Role> roles = Set.of(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN"));
        User user = new User(1L, "Alexander", "alexander@example.com", "123456789",
                        "City", "State", "Country", roles);

        when(mockRepository.findByUsername("alexander@example.com")).thenReturn(Optional.of(user));

        List<String> result = useCase.execute("alexander@example.com");

        assertThat(result).containsExactlyInAnyOrder("ROLE_USER", "ROLE_ADMIN");
    }

    @Test
    void givenNoUser_whenExecute_thenReturnsAnonymousRole() {

        UserRepository mockRepository = mock();
        GetUserRolesUseCase useCase = new GetUserRolesUseCase(mockRepository);

        when(mockRepository.findByUsername("not_exist@example.com")).thenReturn(Optional.empty());

        List<String> result = useCase.execute("not_exist@example.com");

        assertThat(result).containsExactly("ANONYMOUS");

    }
}

