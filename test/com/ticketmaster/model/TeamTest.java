package com.ticketmaster.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {

    Team testTeam;

    @Before
    public void setUp() {
        testTeam = new Team("Test Team", new Location("ABC1"));
    }

    @Test
    public void getFirstUser_shouldReturnFirstMemberInAlphabeticallySortedTeamMembers_whenStoredMembersInTreeSet() throws InvalidActionException {

        User john = new User("john", "password", testTeam);
        User peter = new User("peter", "password", testTeam);
        User bob = new User("bob", "password", testTeam);

        testTeam.addMember(john);
        testTeam.addMember(peter);
        testTeam.addMember(bob);

        assertEquals(bob, testTeam.getFirstUser());
    }

    @Test
    public void addMember_shouldNotThrowInvalidActionException_whenAddUniqueUserLogin() throws InvalidActionException {
        User john = new User("john", "password", testTeam);
        User peter = new User("peter", "password", testTeam);
        testTeam.addMember(john);
        testTeam.addMember(peter);
        System.out.println(testTeam.getMembers());
    }

    @Test (expected = InvalidActionException.class)
    public void addMember_shouldThrowInvalidActionException_whenAddDuplicateUserLogin() throws InvalidActionException {
        User John1 = new User("john", "password", testTeam);
        User John2 = new User("john", "password", testTeam);
        testTeam.addMember(John1);
        testTeam.addMember(John2);
        System.out.println(testTeam.getMembers());
    }

}