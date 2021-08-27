package com.RRS.Utilities;

public class Fare {

    static void getFareAmount() {
        int inc;
        double minimumAmt = 510;
        double[] price = {minimumAmt, 1.90, 1.85, 1.80, 1.75, 1.70, 1.65, 1.60, 1.55, 1.50, 1.45, 1.40, 1.35, 1.30};
        double p = 1.90;

        for (int i = 1; i <= 300; i++) {
            inc = 4;
            System.out.println(i + " - " + (i + inc) + " Rs. " + price[0]);
            i += inc;
        }
        for (int i = 301; i <= 1000; i++) {
            inc = 9;
            if (i >= 301 && i <= 500) {
                System.out.println(i + " " + (i + inc) + " Rs. " + (i * price[1]) + "     " + price[1]);
            } else if (i >= 501 && i <= 750) {
                System.out.println(i + " " + (i + inc) + " Rs. " + (i * price[2]) + "     " + price[2]);
            } else if (i >= 751 && i <= 1000) {
                System.out.println(i + " " + (i + inc) + " Rs. " + (i * price[3]) + "     " + price[3]);
            }
            i += inc;
        }
        for (int i = 1001; i <= 2500; i++) {
            inc = 24;
            if (i >= 1001 && i <= 1250) {
                System.out.println(i + " " + (i + inc) + " Rs. " + (i * price[4]) + "     " + price[4]);
            } else if (i >= 1251 && i <= 1500) {
                System.out.println(i + " " + (i + inc) + " Rs. " + (i * price[5]) + "     " + price[5]);
            } else if (i >= 1501 && i <= 1750) {
                System.out.println(i + " " + (i + inc) + " Rs. " + (i * price[6]) + "     " + price[6]);
            } else if (i >= 1751 && i <= 2000) {
                System.out.println(i + " " + (i + inc) + " Rs. " + (i * price[7]) + "     " + price[7]);
            } else if (i >= 2001 && i <= 2250) {
                System.out.println(i + " " + (i + inc) + " Rs. " + (i * price[8]) + "     " + price[8]);
            } else if (i >= 2251 && i <= 2500) {
                System.out.println(i + " " + (i + inc) + " Rs. " + (i * price[9]) + "     " + price[9]);
            }
            i += inc;
        }
        for (int i = 2501; i <= 3500; i++) {
            inc = 49;
            if (i >= 2501 && i <= 2750) {
                System.out.println(i + " " + (i + inc) + " Rs. " + (i * price[10]) + "     " + price[10]);
            } else if (i >= 2751 && i <= 3000) {
                System.out.println(i + " " + (i + inc) + " Rs. " + (i * price[11]) + "     " + price[11]);
            } else if (i >= 3001 && i <= 3250) {
                System.out.println(i + " " + (i + inc) + " Rs. " + (i * price[12]) + "     " + price[12]);
            } else if (i >= 3251 && i <= 3500) {
                System.out.println(i + " " + (i + inc) + " Rs. " + (i * price[13]) + "     " + price[13]);
            }
            i += inc;
        }
    }

    static double getFareAmount(int JOURNEY_DISTANCE, double INITIAL_PER_KM_FARE, int MINIMUM_DISTANCE_KM, double MINIMUM_DISTANCE_FARE) {
        int inc;
        double minimumAmt = MINIMUM_DISTANCE_FARE;
        double[] price = {minimumAmt, 1.90, 1.85, 1.80, 1.75, 1.70, 1.65, 1.60, 1.55, 1.40, 1.10, 1.00, 0.90, 0.80};
        double p = 0;
        double fare = 0.0;
        for (int i = 1; i <= 300; i++) {
            inc = 4;
            p = price[0];
            System.out.println(i + " - " + (i + inc) + " Rs. " + price[0]);
            if (JOURNEY_DISTANCE == i) {
                fare = p;
            }
            i += inc;

        }
        for (int i = 301; i <= 1000; i++) {
            inc = 9;
            if (i >= 301 && i <= 500) {
                p = i * price[1] - 0.50;
                System.out.println(i + " " + (i + inc) + " Rs. " + (p) + "     " + price[1] + "     Calculated as : " + i + "*" + price[1]);
            } else if (i >= 501 && i <= 750) {
                p = i * price[2];
                System.out.println(i + " " + (i + inc) + " Rs. " + (p) + "     " + price[2] + "     Calculated as : " + i + "*" + price[2]);
            } else if (i >= 751 && i <= 1000) {
                p = i * price[3];
                System.out.println(i + " " + (i + inc) + " Rs. " + (p) + "     " + price[3] + "     Calculated as : " + i + "*" + price[3]);
            }
            i += inc;
            if (JOURNEY_DISTANCE == i) {
                fare = p;
            }

        }
        for (int i = 1001; i <= 2500; i++) {
            inc = 24;
            if (i >= 1001 && i <= 1250) {
                p = i * price[4];
                System.out.println(i + " " + (i + inc) + " Rs. " + (p) + "     " + price[4] + "     Calculated as : " + i + "*" + price[4]);
            } else if (i >= 1251 && i <= 1500) {
                p = i * price[5];
                System.out.println(i + " " + (i + inc) + " Rs. " + (p) + "     " + price[5] + "     Calculated as : " + i + "*" + price[5]);
            } else if (i >= 1501 && i <= 1750) {
                p = i * price[6];
                System.out.println(i + " " + (i + inc) + " Rs. " + (p) + "     " + price[6] + "     Calculated as : " + i + "*" + price[6]);
            } else if (i >= 1751 && i <= 2000) {
                p = i * price[7];
                System.out.println(i + " " + (i + inc) + " Rs. " + (p) + "     " + price[7] + "     Calculated as : " + i + "*" + price[7]);
            } else if (i >= 2001 && i <= 2250) {
                p = i * price[8];
                System.out.println(i + " " + (i + inc) + " Rs. " + (p) + "     " + price[8] + "     Calculated as : " + i + "*" + price[8]);
            } else if (i >= 2251 && i <= 2500) {
                p = i * price[9];
                System.out.println(i + " " + (i + inc) + " Rs. " + (p) + "     " + price[9] + "     Calculated as : " + i + "*" + price[9]);
            }
            i += inc;
            if (JOURNEY_DISTANCE == i) {
                fare = p;
            }

        }
        for (int i = 2501; i <= 3500; i++) {
            inc = 49;
            if (i >= 2501 && i <= 2750) {
                p = i * price[10];
                System.out.println(i + " " + (i + inc) + " Rs. " + (p) + "     " + price[10] + "     Calculated as : " + i + "*" + price[10]);
            } else if (i >= 2751 && i <= 3000) {
                p = i * price[11];
                System.out.println(i + " " + (i + inc) + " Rs. " + (p) + "     " + price[11] + "     Calculated as : " + i + "*" + price[11]);
            } else if (i >= 3001 && i <= 3250) {
                p = i * price[12];
                System.out.println(i + " " + (i + inc) + " Rs. " + (p) + "     " + price[12] + "     Calculated as : " + i + "*" + price[12]);
            } else if (i >= 3251 && i <= 3500) {
                p = i * price[13];
                System.out.println(i + " " + (i + inc) + " Rs. " + (p) + "     " + price[13] + "     Calculated as : " + i + "*" + price[13]);
            }
            i += inc;
            if (JOURNEY_DISTANCE == i) {
                fare = p;
            }

        }
        return fare;
    }

    public static double getFare(int JOURNEY_DISTANCE, double INITIAL_PER_KM_FARE, double MINIMUM_DISTANCE_KM, double MINIMUM_DISTANCE_FARE) {
        if (MINIMUM_DISTANCE_FARE <= 0) {
            return 0;
        }
        double fare = 0;
        int increment = 0;
        if (JOURNEY_DISTANCE > MINIMUM_DISTANCE_KM) {
            for (int i = 1; i <= 3500; i++) {
                if (i >= 1 && i <= 300) {
                    increment = 4;
                    fare = i * (INITIAL_PER_KM_FARE - 0.01);
                   // System.out.println(i + "-" + (i + increment) + "  " + fare);
                    i += increment;
                } else if (i >= 301 && i <= 1000) {
                    increment = 9;
                    fare = i * (INITIAL_PER_KM_FARE - 0.08);
                   // System.out.println(i + "-" + (i + increment) + "  " + fare);
                    i += increment;
                } else if (i >= 1001 && i <= 2500) {
                    increment = 24;
                    fare = i * (INITIAL_PER_KM_FARE - 0.10);
                   // System.out.println(i + "-" + (i + increment) + "  " + fare);
                    i += increment;
                } else if (i >= 2501 && i <= 3500) {
                    increment = 49;
                    fare = i * (INITIAL_PER_KM_FARE - 0.50);
                   // System.out.println(i + "-" + (i + increment) + "  " + fare);
                    i += increment;
                }
                if (i >= JOURNEY_DISTANCE) {
                    break;
                }

            }
        } else {
            return MINIMUM_DISTANCE_FARE;
        }

        return fare;
    }

    public static double calculatePercentage(double obtained) {
        return obtained / 100;
    }

    public static void main(String[] args) {
        double amt = getFare(985, 0, 0, 0);
        System.out.println(Math.round(amt));

    }

}


/*

Mail/Express Trains
3AC - upto 300km   428/-

SL  - upto 15km    40/-



 */
