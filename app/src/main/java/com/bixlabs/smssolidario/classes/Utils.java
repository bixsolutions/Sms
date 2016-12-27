package com.bixlabs.smssolidario.classes;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Utility methods
 */
public class Utils {

  /**
   * Get an organization name based on its phone number
   * @param phoneNumber The phone number
   * @param organizations The organizations list
   * @return The organization name as String
     */
  public static String getOrganizationNameFromNumber(String phoneNumber, String[] organizations) {
    String organizationName = null;

    for (Map.Entry<String, String[]> organization : Constants.ORGANIZATION_INFO.entrySet() ) {
      if (phoneNumber.equals(organization.getValue()[1])) {
        for(String orgs : organizations) {
          String[] splittedOrg = orgs.split("\\|", 2);
          if (splittedOrg[0].equals(organization.getKey())) {
            organizationName = splittedOrg[1];
            break;
          }
        }
        break;
      }
    }

    return organizationName;
  }

  /**
   * Private helper method to convert timestamps to formatted dates
   * @param dateInMillis The timestamp
   * @return The formatted date as String
   */
  public static String formatDate(Long dateInMillis) {
    DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
    Date netDate = (new Date(dateInMillis));
    return sdf.format(netDate);
  }

}
