package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

@ActiveProfiles(value = {Profiles.HSQL_DB,Profiles.DATAJPA})
public class MealServiceTestHSQL extends MealServiceTest {
}
