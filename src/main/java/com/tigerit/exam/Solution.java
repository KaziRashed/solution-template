package com.tigerit.exam;


import static com.tigerit.exam.IO.*;
import java.util.*;
/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
public class Solution implements Runnable {
    @Override
    public void run() {
        String table_name[] = new String[12];
        int nc[] = new int[110];
        int nd[] = new int[110];
        String[][] column_name = new String[12][110];
        int[][][] data = new int[12][110][110];
        int[] result_first_table = new int[17000];
        int[] result_second_table = new int[17000];
        int counter1 = 0;
        int nq;
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int loop_counter_i = 1; loop_counter_i <= T; loop_counter_i++) {
            int nt = sc.nextInt();

            for (int loop_counter_j = 1; loop_counter_j <= nt; loop_counter_j++) {
                table_name[loop_counter_j] = sc.next();
                nc[loop_counter_j] = sc.nextInt();
                nd[loop_counter_j] = sc.nextInt();

                for (int loop_counter_k = 1; loop_counter_k <= nc[loop_counter_j]; loop_counter_k++) {
                    column_name[loop_counter_j][loop_counter_k] = sc.next();
                }

                for (int loop_counter_p = 1; loop_counter_p <= nd[loop_counter_j]; loop_counter_p++) {
                    for (int loop_counter_q = 1; loop_counter_q <= nc[loop_counter_j]; loop_counter_q++) {
                        data[loop_counter_j][loop_counter_p][loop_counter_q] = sc.nextInt();
                    }
                }
            }
            System.out.println("Test: " + loop_counter_i);
            nq = sc.nextInt();
            sc.nextLine();

            for (int loop_counter_r = 1; loop_counter_r <= nq; loop_counter_r++) {
                counter1 = 0;
                String query_line_1 = sc.nextLine();
                String query_line_2 = sc.nextLine();
                String query_line_3 = sc.nextLine();
                String query_line_4 = sc.nextLine();
                //System.out.println(query_line_4);
                String space = " ";
                String[] tokens2 = query_line_2.split(space);
                String[] tokens3 = query_line_3.split(space);

                String comma_space = "[ ,]+";
                String[] tokens1 = query_line_1.split(comma_space);

                String[] tokens4 = query_line_4.split(space);
                int first_table_index = 0, second_table_index = 0;
                for (int loop_counter_j = 1; loop_counter_j <= nt; loop_counter_j++) {
                    if (table_name[loop_counter_j].equals(tokens2[1])) {
                        first_table_index = loop_counter_j;
                        break;
                    }

                }
                for (int loop_counter_j = 1; loop_counter_j <= nt; loop_counter_j++) {
                    if (table_name[loop_counter_j].equals(tokens3[1])) {
                        second_table_index = loop_counter_j;
                        break;
                    }

                }
                //System.out.println(first_table_index);
                String dot = "\\.";
                String[] tokens5 = tokens4[1].split(dot);
                String[] tokens6 = tokens4[3].split(dot);
                int find_column_first_table = 0;
                int find_column_second_table = 0;
                
                for (int loop_counter_k = 1; loop_counter_k <= nc[first_table_index]; loop_counter_k++) {
                    if (column_name[first_table_index][loop_counter_k].equals(tokens5[1])) {
                        find_column_first_table = loop_counter_k;
                        break;
                    }
                }
                for (int loop_counter_k = 1; loop_counter_k <= nc[second_table_index]; loop_counter_k++) {
                    if (column_name[second_table_index][loop_counter_k].equals(tokens6[1])) {
                        find_column_second_table = loop_counter_k;
                        break;
                    }
                }
                int counter = 0;
                for (int outer_join = 1; outer_join <= nd[first_table_index]; outer_join++) {
                    for (int inner_join = 1; inner_join <= nd[second_table_index]; inner_join++) {
                        if (data[first_table_index][outer_join][find_column_first_table] == data[second_table_index][inner_join][find_column_second_table]) {
                            counter++;
                            result_first_table[counter] = outer_join;
                            result_second_table[counter] = inner_join;
                            //System.out.println(outer_join+" "+inner_join);
                        }
                    }
                }
                int[][] printed_result = new int[220][2];
                //System.out.println(tokens1.length);
                if (tokens1[1].equals("*")) {
                    for (int i = 0; i < nc[first_table_index]; i++) {
                        printed_result[counter1][0] = 1;
                        printed_result[counter1][1] = i + 1;
                        counter1++;
                    }
                    for (int i = 0; i < nc[second_table_index]; i++) {
                        printed_result[counter1][0] = 2;
                        printed_result[counter1][1] = i + 1;
                        counter1++;
                    }
                } else {
                    for (int i = 1; i < tokens1.length; i++) {
                        String[] tokens7 = tokens1[i].split(dot);
                        //System.out.println(tokens1[i]);
                        if (tokens2[2].equals(tokens7[0])) {
                            for (int j = 1; j <= nc[first_table_index]; j++) {
                                if (column_name[first_table_index][j].equals(tokens7[1])) {
                                    printed_result[counter1][0] = 1;
                                    printed_result[counter1][1] = j;
                                    counter1++;
                                    break;
                                }


                            }
                        } else {
                           // System.out.println(nc[second_table_index]);
                            for (int j = 1; j <= nc[second_table_index]; j++) {
                                //System.out.println(tokens7[0]);
                                if (column_name[second_table_index][j].equals(tokens7[1])) {
                                    printed_result[counter1][0] = 2;
                                    printed_result[counter1][1] = j;
                                    counter1++;
                                    break;
                                }


                            }

                        }

                    }
                }

                ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
                for (int i = 1; i <= counter; i++) {
                    ArrayList<Integer> tmp = new ArrayList<Integer>();
                    //System.out.println(printed_result[5][0]);
                    for (int j = 0; j < counter1; j++) {
                        
                        if (printed_result[j][0] == 1) {
                            //System.out.println(data[first_table_index][result_first_table[counter]][printed_result[j][1]]);
                            tmp.add(data[first_table_index][result_first_table[i]][printed_result[j][1]]);
                        } else {
                            //System.out.println(data[second_table_index][result_second_table[counter]][printed_result[j][1]]);
                            tmp.add(data[second_table_index][result_second_table[i]][printed_result[j][1]]);

                        }
                    }
                    ans.add(tmp);
                }
                Collections.sort(ans, new Comparator<ArrayList<Integer>>() {

                    @Override
                    public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
                        int i, j;
                        for (i = 0, j = 0; i < list1.size() && j < list2.size(); i++, j++) {
                            if (list1.get(i) < list2.get(j)) {
                                return 0;
                            } else if (list1.get(i) > list2.get(j)) {
                                return 1;
                            }
                        }
                        if (i == list1.size()) {
                            return 0;
                        }
                        return 1;
                    }
                });
                
                for (int i = 0; i < counter1; i++) {
                    if (i > 0) {
                        System.out.print(" ");
                    }
                    if (printed_result[i][0] == 1) {
                        System.out.print(column_name[first_table_index][printed_result[i][1]]);
                    } else {
                        System.out.print(column_name[second_table_index][printed_result[i][1]]);
                    }
                }
                System.out.println();
        for (int i = 0; i < ans.size(); i++) {
            ArrayList<Integer> tmp = ans.get(i);
            for(int j = 0; j < tmp.size(); j++)
            {
                if(j > 0) System.out.print(" ");
                System.out.print(tmp.get(j));
            }
            System.out.println();
        }
                System.out.println();
            }

            }

    }
}
