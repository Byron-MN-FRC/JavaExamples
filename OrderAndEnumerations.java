class Order {
    // Define the days of the week in an enumeration
    public enum Day {
        Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
    }

    private final static Day[] days = Day.values();

    public static void main(String[] args) {
        // this example shows you how to assign a Day to a variable.
        // Day is defined above as an enumeration of the days of the week
        Day churchDay = Day.Sunday;
        String outputString = String.format("I go to church on %s", getDayName(churchDay));
        System.out.println(outputString);

        // Lets say that we are testing to see if it is OK to schedule a blood test for
        // a patient.
        // The blood test has a requirement that the patient has been fasting since the
        // previous day.

        // So, the first example, the patient started fasting on Monday, can I schedule
        // a blood test for Tuesday?
        Day patientStartedFasting;
        Day dayToScheduleAppointment;
        Boolean OkToSchedule;

        patientStartedFasting = Day.Monday;
        dayToScheduleAppointment = Day.Tuesday;

        OkToSchedule = testDayToSchedule(patientStartedFasting, dayToScheduleAppointment);

        // For the Second example, the patient started fasting on Saturday, can I
        // schedule a blood test for Sunday?
        // NOTE: I can pass the Day of the week directly into the testDayToSchedule
        // method and not use variable as in prev example.
        OkToSchedule = testDayToSchedule(Day.Saturday, Day.Sunday);

        // Now lets try fasting starting on Monday and Test day as wednesday
        OkToSchedule = testDayToSchedule(Day.Monday, Day.Wednesday);
    }

    /**
     * This function returns the textual Name of the Day passed to it.
     * 
     * @param d - The day of the week we want the name of.
     * @return the textual Name of the Day passed to it.
     */
    private static String getDayName(Day d) {
        return days[d.ordinal()].name();
    }

    /**
     * This routine determines if the a day(dayToTest) follows another day(startDay)
     * 
     * @param startDay  - The day we use as the starting day
     * @param dayToTest - The day that we test to see if it is the day after the
     *                  startDay day
     * @return returns a boolean stating if the dayToTest follows the startDay
     */
    private static boolean validNextDay(Day startDay, Day dayToTest) {
        // When you ask for the .ordinal() of an enumeration instance
        // (startDay.ordinal())
        // you are given back its position in the list (Starts with 0).
        // looking at the enum Day, the ordinal for Sunday would be 0, Monday = 1,
        // Tuesday = 2, ..., Saturday = 6
        // the following formula takes the ordinal of the startDay day and adds 1 to it.
        // Then it
        // calculates the modulus using the total number of days(7). The modulus is the
        // remainder when you divide by 7 in this case.
        // example: startDay = Saturday. The ordinal of Saturday is 6. 6+1=7, 7/7=1, The
        // remainder is 0 which is Sunday's ordinal
        // so Sunday is the day after Saturday.
        int ordinalDayAfterStartDay = (startDay.ordinal() + 1) % 7;

        return (ordinalDayAfterStartDay == dayToTest.ordinal());
    }

    /**
     * 
     * @param fastStart     The day the patient started fasting
     * @param dayToSchedule The requested day to schedule the appointment
     * @return returns true if it ok to schedule the appointment, otherwise returns false.
     */
    private static boolean testDayToSchedule(Day fastStart, Day dayToSchedule) {
        System.out.println(String.format("\n\nThe patient started fasting on %s", getDayName(fastStart)));
        System.out.println(String.format("Is it ok to schedule the test on %s?", getDayName(dayToSchedule)));

        if (validNextDay(fastStart, dayToSchedule)) {
            System.out.println(String.format("YES, please schedule the blood test for %s.", getDayName(dayToSchedule)));
            return true;
        } else {
            System.out.println(String.format("No, Do not schedule.  %s is not the day after %s!",
                    getDayName(dayToSchedule), getDayName(fastStart)));
            return false;
        }
    }

}