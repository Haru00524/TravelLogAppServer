package zarazio.travel.android.bean;

import java.sql.Date;

public class TravelStory {
   private String user_id; // ȸ�����̵�
   private int group_Code; // �׷��ڵ�
   private String travel_title; // ��������
   private Date start_Date; // ���������
   private Date end_Date; // ���ึ������
   private int coin_Limit; // �����ѵ�
   private String sc_Division; // ���񱸺�
   private String expense_Content; // ���⳻��
   private int expense_Cost; // ����ݾ�
   private Date expense_Date; // ���⳯¥
   
   public Date getExpense_Date() {
   return expense_Date;
}
public void setExpense_Date(Date expense_Date) {
   this.expense_Date = expense_Date;
}
public String getSc_Division() {
   return sc_Division;
   }
   public void setSc_Division(String sc_Division) {
      this.sc_Division = sc_Division;
   }
   
   private int expense_Code; // �����ڵ�
      
      public int getExpense_Code() {
      return expense_Code;
   }
   public void setExpense_Code(int expense_Code) {
      this.expense_Code = expense_Code;
   }
   public int getGroup_Code() {
      return group_Code;
   }
   public void setGroup_Code(int group_Code) {
      this.group_Code = group_Code;
   }
   public String getExpense_Content() {
      return expense_Content;
   }
   public void setExpense_Content(String expense_Content) {
      this.expense_Content = expense_Content;
   }
   public int getExpense_Cost() {
      return expense_Cost;
   }
   public void setExpense_Cost(int expense_Cost) {
      this.expense_Cost = expense_Cost;
   }
   public Date getStart_Date() {
      return start_Date;
   }
   public int getCoin_Limit() {
      return coin_Limit;
   }
   public void setCoin_Limit(int coin_Limit) {
      this.coin_Limit = coin_Limit;
   }
   public void setStart_Date(Date start_Date) {
      this.start_Date = start_Date;
   }
   public Date getEnd_Date() {
      return end_Date;
   }
   public void setEnd_Date(Date end_Date) {
      this.end_Date = end_Date;
   }
   public String getUser_id() {
      return user_id;
   }
   public void setUser_id(String user_id) {
      this.user_id = user_id;
   }
   public String getTravel_title() {
      return travel_title;
   }
   public void setTravel_title(String travel_title) {
      this.travel_title = travel_title;
   }

   public String toString() {
      return    " travel_title : " + travel_title + 
             " start_Date : " + start_Date + 
            " end_Date : " + end_Date;
   }
   
}