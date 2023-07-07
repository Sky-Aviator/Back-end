package dev.patricksilva.model.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dev.patricksilva.model.enums.ERole;

public class RoleTest {

    @Test
    public void testGetId() {
        String id = "123";
        Role role = new Role();
        role.setId(id);

        assertEquals(id, role.getId());
    }

    @Test
    public void testGetName() {
        ERole roleName = ERole.ROLE_ADMIN;
        Role role = new Role();
        role.setName(roleName);

        assertEquals(roleName, role.getName());
    }

    @Test
    public void testSetId() {
        String id = "456";
        Role role = new Role();
        role.setId(id);

        assertEquals(id, role.getId());
    }

    @Test
    public void testSetName() {
        ERole roleName = ERole.ROLE_USER;
        Role role = new Role();
        role.setName(roleName);

        assertEquals(roleName, role.getName());
    }
}