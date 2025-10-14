package edu.wisc.my.messages.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 * Utility for parsing isMemberOf header.
 */
@Component
public class IsMemberOfHeaderParser {

  public Set<String> groupsFromHeaderValue(String headerValue) {

    if (null == headerValue) {
      return Collections.EMPTY_SET;
    }

    Set<String> userGroups = new HashSet<>();

    String[] groupsArray = headerValue.split(";");
    userGroups.addAll(Arrays.asList(groupsArray));

    return userGroups;
  }
}
