public bool dbConnect(){
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    StrictMode.setThreadPolicy(policy);
    try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(ip, user, password);
        return true;
    } catch (Exception e) {
        return false;
    }
}

public int createID(){// called when submit button is pressed and there is a connection
    bool connectionStatus = dbConnect();
    if (connectionStatus){
        try{
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("select id from records order by id desc limit 1");
            int oldID = rs.getInt("id");
        } catch {
            // Error message
        }
        con.close();
        return oldID + 1;
    } else {
        con.close();
        // wait for connection
        // data will be sent to local db
        // id will be made when local db get pushed to mysql server
    }
}

public void sendData(){
    bool connectionStatus = dbConnect();
    if (connectionStatus){
        String ifEmpty = "select * from records";
        Cursor mCursor = db.rawQuery(ifEmpty, null);
        int count = mCursor.getCount();
        if (count > 0){
            String push
        }
        Statement stmt = con.createStatement();
        String value = id + ", \'" + type + "\', \"" + firstName + "\", \"" + lastName + "\", \"" + address + "\", \"" + city + "\", " + zip + ", \"" + phone + "\", \"" + email + "\", \"" + schoolDistrict + "\", \"" + schoolBuilding + "\", \"" + teacher + "\", \"" + instrument + "\", \"" + brand + "\", \"" + serialNumber + "\", " + mouthPiece + ", \"" + description + "\", \"" + dueDate + "\", " + price + ", " + mpCoverage + ", \"" + status + "\", \"" + dateCreated + "\", \"" + dateReceived + "\", " + employeeID;
        stmt.executeUpdate("insert into records values (" + value + ")");
    } else {
        con.close();
        
    }
}