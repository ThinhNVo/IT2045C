import java.util.Scanner;

public class SafeInputOBJ {
    private Scanner pipe;
    public SafeInputOBJ(Scanner pipe) {
        this.pipe = pipe;
    }

        public String getNonZeroLenString(String prompt) {
            String retString = "";

            do {
                System.out.print("\n" + prompt + ": ");
                retString = pipe.nextLine();
            } while(retString.isEmpty());

            return retString;
        }

        public int getRangedInt(String prompt, int low, int high) {
            int retVal = 0;
            String trash = "";
            boolean done = false;

            do {
                System.out.print("\n" + prompt + "[" + low + "-" + high + "]: ");
                if (pipe.hasNextInt()) {
                    retVal = pipe.nextInt();
                    pipe.nextLine();
                    if (retVal >= low && retVal <= high) {
                        done = true;
                    } else {
                        System.out.println("\nNumber is out of range [" + low + "-" + high + "]: " + retVal);
                    }
                } else {
                    trash = pipe.nextLine();
                    System.out.println("You must enter an int: " + trash);
                }
            } while(!done);

            return retVal;
        }

        public int getInt( String prompt) {
            int retVal = 0;
            String trash = "";
            boolean done = false;

            do {
                System.out.print("\n" + prompt + ": ");
                if (pipe.hasNextInt()) {
                    retVal = pipe.nextInt();
                    pipe.nextLine();
                    done = true;
                } else {
                    trash = pipe.nextLine();
                    System.out.println("You must enter an int: " + trash);
                }
            } while(!done);

            return retVal;
        }

        public double getRangedDouble( String prompt, int low, int high) {
            double retVal = (double)0.0F;
            String trash = "";
            boolean done = false;

            do {
                System.out.print("\n" + prompt + "[" + low + "-" + high + "]: ");
                if (pipe.hasNextDouble()) {
                    retVal = pipe.nextDouble();
                    pipe.nextLine();
                    if (retVal >= (double)low && retVal <= (double)high) {
                        done = true;
                    } else {
                        System.out.println("\nNumber is out of range [" + low + "-" + high + "]: " + retVal);
                    }
                } else {
                    trash = pipe.nextLine();
                    System.out.println("You must enter a double: " + trash);
                }
            } while(!done);

            return retVal;
        }

        public double getDouble( String prompt) {
            double retVal = (double)0.0F;
            String trash = "";
            boolean done = false;

            do {
                System.out.print("\n" + prompt + ": ");
                if (pipe.hasNextDouble()) {
                    retVal = pipe.nextDouble();
                    pipe.nextLine();
                    done = true;
                } else {
                    trash = pipe.nextLine();
                    System.out.println("You must enter a double: " + trash);
                }
            } while(!done);

            return retVal;
        }

        public boolean getYNConfirm( String prompt) {
            boolean retVal = true;
            String response = "";
            boolean gotAVal = false;

            do {
                System.out.print("\n" + prompt + " [Y/N] ");
                response = pipe.nextLine();
                if (response.equalsIgnoreCase("Y")) {
                    gotAVal = true;
                    retVal = true;
                } else if (response.equalsIgnoreCase("N")) {
                    gotAVal = true;
                    retVal = false;
                } else {
                    System.out.println("You must answere [Y/N]! " + response);
                }
            } while(!gotAVal);

            return retVal;
        }

        public String getRegExString( String prompt, String regExPattern) {
            String response = "";
            boolean gotAVal = false;

            do {
                System.out.print("\n" + prompt + ": ");
                response = pipe.nextLine();
                if (response.matches(regExPattern)) {
                    gotAVal = true;
                } else {
                    System.out.println("\n" + response + " must match the pattern " + regExPattern);
                    System.out.println("Try again!");
                }
            } while(!gotAVal);

            return response;
        }
    }



