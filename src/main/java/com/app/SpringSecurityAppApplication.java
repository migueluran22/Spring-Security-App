package com.app;

import com.app.persistence.entity.PermissionEntity;
import com.app.persistence.entity.RoleEntity;
import com.app.persistence.entity.RoleEnum;
import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args -> {
			PermissionEntity createPermission = PermissionEntity.builder()
					/* CREATE PERIMISSION */
					.name("CREATE")
					.build();

			PermissionEntity readPermission = PermissionEntity.builder()
					/* READ PERIMISSION */
					.name("READ")
					.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					/* UPDATE PERIMISSION */
					.name("UPDATE")
					.build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					/* DELETE PERIMISSION */
					.name("DELETE")
					.build();

			PermissionEntity refactorPermission = PermissionEntity.builder()
					/* REFACTOR PERIMISSION */
					.name("REFACTOR")
					.build();

			/* Create ROLES */
			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission, readPermission))
					.build();

			RoleEntity roleInvited = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionList(Set.of(readPermission))
					.build();

			RoleEntity roleDeveloper = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			/* Create USERS */
			UserEntity userSantiago = UserEntity.builder()
					.username("santiago")
					.password("$2a$10$HqiwykWHwEFQfCTzgMF6/Oy2Sm.A7lSwhAjuPOY/3bAeTfDOny7yW")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userDaniel = UserEntity.builder()
					.username("daniel")
					.password("$2a$10$HqiwykWHwEFQfCTzgMF6/Oy2Sm.A7lSwhAjuPOY/3bAeTfDOny7yW")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			UserEntity userAndrea = UserEntity.builder()
					.username("andrea")
					.password("$2a$10$HqiwykWHwEFQfCTzgMF6/Oy2Sm.A7lSwhAjuPOY/3bAeTfDOny7yW")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleInvited))
					.build();

			UserEntity userAnyi = UserEntity.builder()
					.username("anyi")
					.password("$2a$10$HqiwykWHwEFQfCTzgMF6/Oy2Sm.A7lSwhAjuPOY/3bAeTfDOny7yW")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			userRepository.saveAll(List.of(userSantiago, userDaniel, userAndrea, userAnyi));
		};
	}
}
