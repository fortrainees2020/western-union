
# Mongodb Setup 
## 1. Download
		- Go to MongoDB Community Server Download
.		- elect Windows x64 MSI and download.

## 2. Install
   		- Run the .msi installer as Administrator.
   		- Choose Complete Setup.

Tick Install MongoDB as a Service (recommended).
(Optional) Tick Install MongoDB Compass.
Finish installation.

## 3. Create Data Folder (if needed)

If not created automatically, open Command Prompt (Admin):
```
md C:\data\db
```
## 4. Install MongoDB Shell (mongosh)
	- Download MongoDB Shell
 	- Install it.
  	- Add its bin folder to PATH (Environment Variables).

## 5. Start MongoDB
If installed as a service:
```
net start MongoDB
```

Or run manually:
```
"C:\Program Files\MongoDB\Server\<version>\bin\mongod.exe" --dbpath="C:\data\db"
```
## 6. Connect
Open new terminal:
```  mongosh ```

You’re now connected to MongoDB at mongodb://localhost:27017.


# MongoDB Lab Manual - Queries & Tasks
======================================================

A. Database & Collection Management (DDL)
-----------------------------------------
1) Show all databases
   ```
   show dbs
   ```

3) Switch to (or create) database 'company'
 ```
   use company
```

5) Create a collection 'employees'
   ```
   db.createCollection("employees")
   ```

6) Create a capped collection (1MB, 1000 docs)
   ```db.createCollection("events", { capped: true, size: 1048576, max: 1000 }) ```

7) Drop a collection
   ``` db.employees.drop()```
8) Drop current database
  ``` db.dropDatabase()
```
9) use mydb
```
 db.oldName.insertOne({ empId: 1, name: "Ashu" })
```

Rename a collection
 ```
 db.employees.renameCollection("myemp")
```

8) View current database
   ```
   db
    ```

10) List collections
    
   ``` 
   show collections
```


11) Collection stats
``` db.employees.insertOne({ empId: 1, name: "John", dept: "IT" })
    db.employees.stats() ```


B. Insert Operations
--------------------
11) Insert one document
   ``` db.employees.insertOne({ name: "John", age: 30, dept: "HR" })
    db.employees.find() ```

12) Insert many documents
   ``` db.employees.insertMany([
      { name: "Alice", age: 25, dept: "IT" },
      { name: "Bob", age: 28, dept: "Finance" }
    ]) ```

13) Insert with nested document
    ``` db.employees.insertOne({ name: "Sara", address: { city: "NY", zip: 12345 } }) ```

14) Insert with array
    ``` db.employees.insertOne({ name: "Mike", skills: ["Java", "Python", "MongoDB"] }) ```

15) Insert with custom _id
   ``` db.employees.insertOne({ _id: 1001, name: "Leo" }) ```

16) Insert with date
   ``` db.employees.insertOne({ name: "Rita", joined: new Date(), dept: "IT" }) ```

17) Insert if not exists (upsert via update)
   ``` db.employees.updateOne({ _id: 5001 }, { $setOnInsert: { name: "Zed" } }, { upsert: true }) ```

18) Insert document with decimal
    ``` db.prices.insertOne({ sku: "P100", price: NumberDecimal("19.99") }) ```
    
   ``` db.employees.find().limit(5)```

19) Insert document with boolean
   ``` db.flags.insertOne({ feature: "beta", enabled: true })```

20) Insert document with ISODate string
    ``` db.logs.insertOne({ level: "INFO", at: ISODate("2024-01-01T10:00:00Z") }) ```

```  db.employees.insertOne({
  name: "Tom",
  department: "IT",
  salary: 55000
}) ```


C. Basic Reads (find / findOne)
-------------------------------
21) Find all documents
    ``` db.employees.find() ```

22) Find one document
    ``` db.employees.findOne() ```

23) Find with equality
  ```  db.employees.find({ dept: "IT" }) ```

24) AND condition
  ```  db.employees.find({ dept: "IT", age: 25 }) ```

25) OR condition
    ```db.employees.find({ $or: [{ dept: "IT" }, { age: 28 }] }) ```

26) Greater than
  ```  db.employees.find({ age: { $gt: 25 } }) ```

27) Less than
   ``` db.employees.find({ age: { $lt: 30 } }) ```

28) Greater or equal
    ```db.employees.find({ age: { $gte: 30 } }) ```

29) Not equal
   ``` db.employees.find({ dept: { $ne: "HR" } }) ```

30) IN condition
   ``` db.employees.find({ dept: { $in: ["IT", "Finance"] } }) ```


D. Projection (Select Fields)
-----------------------------
31) Include only name
   ``` db.employees.find({}, { name: 1 }) ```

32) Include name and dept (exclude _id)
    ``` db.employees.find({}, { _id: 0, name: 1, dept: 1 }) ```

33) Exclude a field
   ``` db.employees.find({}, { address: 0 }) ```

34) Project computed field
   ``` db.sales.aggregate([
      { $project: { total: { $multiply: ["$qty", "$price"] } } }
    ]) ```

35) Slice array field
    ``` db.posts.find({}, { comments: { $slice: 5 } }) ```


E. Sorting, Limiting, Skipping
------------------------------
36) Sort by age ascending
   ``` db.employees.find().sort({ age: 1 }) ```

37) Sort by age descending
   ``` db.employees.find().sort({ age: -1 }) ```

38) Limit 5
   ``` db.employees.find().limit(5) ```

39) Skip 10
   ``` db.employees.find().skip(10)```

40) Pagination (page 3, size 10)
   ``` db.employees.find().skip(20).limit(10)```


F. Updates
----------
41) Update one field
   ``` db.employees.updateOne({ name: "John" }, { $set: { age: 31 } })```

42) Update many documents
   ``` db.employees.updateMany({ dept: "IT" }, { $set: { status: "active" } })```

43) Increment a field
    ```db.employees.updateOne({ name: "John" }, { $inc: { age: 1 } })```

```db.employees.updateOne(
  { name: "John" },
  { $set: { salary: 60000 } }
)```

```db.employees.updateOne(
  { name: "Alice" },
  { $set: { salary: 50000 } }
)```

```db.employees.updateOne(
  { name: "Bob" },
  { $set: { salary: 70000 } }
)```


44) Rename a field
  ```  db.employees.updateMany({}, { $rename: { dept: "department" } })```

45) Unset (remove) a field
   ``` db.employees.updateMany({}, { $unset: { status: "" } })```

46) Multiply numeric field
  ```  db.products.updateMany({}, { $mul: { price: 1.1 } })```

47) Min/Max operators
   ``` db.stats.updateOne({ _id: 1 }, { $min: { low: 10 }, $max: { high: 100 } })v

48) Set current date
   ``` db.docs.updateOne({ _id: 1 }, { $currentDate: { updatedAt: true } })```

49) Upsert example
  ```  db.employees.updateOne({ name: "Nora" }, { $set: { dept: "Ops" } }, { upsert: true })```

50) Array operators: addToSet
  ```  db.employees.updateOne({ name: "Mike" }, { $addToSet: { skills: "Docker" } })```


G. Deletes
----------
51) Delete one
   ``` db.employees.deleteOne({ name: "John" })```

52) Delete many
   ``` db.employees.deleteMany({ dept: "Finance" })```

53) Delete all
  ```  db.temp.deleteMany({})```


H. Array Queries & Updates
--------------------------
54) Match array element (exact)
   ``` db.employees.find({ skills: "Java" })```

55) Match any element > 80
   ``` db.scores.find({ points: { $elemMatch: { $gt: 80 } } })```

56) Array size
    ```db.employees.find({ skills: { $size: 3 } })```

57) Push to array
 ```   db.employees.updateOne({ name: "Mike" }, { $push: { skills: "AWS" } })```

58) Pull from array
 ```   db.employees.updateOne({ name: "Mike" }, { $pull: { skills: "Java" } })```

59) Push with each + sort
  ```  db.arr.updateOne({ _id: 1 }, { $push: { vals: { $each: [5,3,9], $sort: 1 } } })```

60) Pop last element
  ```  db.arr.updateOne({ _id: 1 }, { $pop: { vals: 1 } })```


I. Aggregation Basics
---------------------
61) Count documents
    db.employees.countDocuments()

62) Group by department
   ``` db.employees.aggregate([
      { $group: { _id: "$dept", total: { $sum: 1 } } }
    ])```

63) Average age by department
  ```  db.employees.aggregate([
      { $group: { _id: "$dept", avgAge: { $avg: "$age" } } }
    ])
```
64) Max salary
  ```  db.employees.aggregate([
      { $group: { _id: null, maxSalary: { $max: "$salary" } } }
    ])```

65) Min salary
   ``` db.employees.aggregate([
      { $group: { _id: null, minSalary: { $min: "$salary" } } }
    ])```

66) Match then group
   ``` db.employees.aggregate([
      { $match: { age: { $gt: 25 } } },
      { $group: { _id: "$dept", count: { $sum: 1 } } }
    ])```

67) Sort in pipeline
   ``` db.employees.aggregate([{ $sort: { age: -1 } }])```

68) Project fields
   ``` db.employees.aggregate([{ $project: { name: 1, dept: 1, _id: 0 } }])```

69) Add computed field
   ``` db.sales.aggregate([{ $addFields: { total: { $multiply: ["$qty", "$price"] } } }])```

70) Limit stage
  ```  db.employees.aggregate([{ $limit: 10 }])```


J. Aggregation – Joins & Arrays
-------------------------------
71) $lookup (join employees with departments)

The $lookup stage is used to perform a left outer join between two collections.
Here, each document from the employees collection is joined with the departments collection where the employees.deptId matches departments._id.
The joined department data is added into a new array field deptInfo.
```

    db.employees.aggregate([
      { $lookup: {
          from: "departments",
          localField: "deptId",
          foreignField: "_id",
          as: "deptInfo"
      }}
    ])```

72) $unwind array
The $unwind stage takes an array field (e.g., skills) and outputs one document per array element.
This is useful when you need to treat each element of an array as a separate row for filtering or grouping.

 ```   db.employees.aggregate([
      { $unwind: "$skills" }
    ])
```
73) $lookup + $unwind + $project

This example shows a full join + flatten + projection pipeline:
$lookup joins orders with customers on custId.
$unwind removes the array wrapper from the joined cust field, turning it into an object.
$project reshapes the result to include only orderNo and the customer’s name (renamed as customer).

  ```  db.orders.aggregate([
      { $lookup: { from: "customers", localField: "custId", foreignField: "_id", as: "cust" } },
      { $unwind: "$cust" },
      { $project: { _id: 0, orderNo: 1, customer: "$cust.name" } }
    ])```

74) $group after unwind (count by skill)
This query counts how many employees know each skill:
$unwind splits the skills array into individual documents.
$group groups by skill name (_id: "$skills") and counts occurrences.
$sort orders skills by frequency in descending order.

```
    db.employees.aggregate([
      { $unwind: "$skills" },
      { $group: { _id: "$skills", count: { $sum: 1 } } },
      { $sort: { count: -1 } }
    ])```

75) $facet (multiple pipelines)
The $facet stage allows you to run multiple aggregation pipelines in parallel on the same input documents.
Each sub-pipeline produces its own result set, and all results are returned together.
In this example:
byDept groups employees by department and counts them.
ages buckets employees into age ranges (20–29, 30–39, 40–49, 50–59, and 60+).
```
    db.employees.aggregate([
      { $facet: {
          byDept: [ { $group: { _id: "$dept", total: { $sum: 1 } } } ],
          ages:   [ { $bucket: { groupBy: "$age", boundaries: [20,30,40,50,60], default: "60+" } } ]
      }}
    ])
```

K. Indexing & Performance
-------------------------
76) Create single-field index
  ```  db.employees.createIndex({ name: 1 }) // ascending name```

77) Create compound index
   ``` db.employees.createIndex({ dept: 1, age: -1 })

78) Text index
	```db.articles.insertMany([
 	 { title: "MongoDB Basics", body: "Learn how to use MongoDB for beginners" },
  	{ title: "Advanced MongoDB", body: "Indexes, Aggregation, and Performance Tuning" },
  	{ title: "Node.js Guide", body: "Using Node.js with MongoDB" }
	])

  ```  db.articles.createIndex({ title: "text", body: "text" })```

79) Geo index (2dsphere)
 ```   db.places.createIndex({ location: "2dsphere" })```

80) View indexes
   ``` db.employees.getIndexes()```

81) Drop index by name
   ``` db.employees.dropIndex("name_1")```

82) Explain a query
   ``` db.employees.find({ name: "John" }).explain("executionStats")```


L. Text & Regex Search
----------------------
83) Text search
   ``` db.articles.find({ $text: { $search: "MongoDB performance" } })```

84) Text search with score
 ```   db.articles.find(
      { $text: { $search: "scaling" } },
      { score: { $meta: "textScore" } }
    ).sort({ score: { $meta: "textScore" } })```

85) Regex starts with A
   ``` db.employees.find({ name: /^A/ })```

86) Case-insensitive regex
  ```  db.employees.find({ name: /john/i })```


M. Schema & Validation
----------------------
87) Create collection with validator
   ``` db.createCollection("validated", {
      validator: { $jsonSchema: {
        bsonType: "object",
        required: ["name", "age"],
        properties: {
          name: { bsonType: "string" },
          age:  { bsonType: "int", minimum: 0 }
        }
      }}
    })```

88) Bypass document validation (admin need)
 ```   db.runCommand({
      insert: "validated",
      documents: [{ name: "Temp", age: 1 }],
      bypassDocumentValidation: true
    })```


N. Distinct, Exists, Types
--------------------------
89) Distinct departments
    ```db.employees.distinct("dept")
```
90) Field exists
  ```  db.employees.find({ manager: { $exists: true } })```

91) Type query (int)
  ```  db.employees.find({ age: { $type: "int" } })```

92) Null vs missing
    db.employees.find({ middleName: null })  // matches null or missing
    // To find only missing:
   ```  db.employees.find({ middleName: { $exists: false } })```


O. Dates & Time
---------------
93) Find documents created today
  ```  db.logs.find({ at: { $gte: ISODate(new Date().toISOString().slice(0,10) + "T00:00:00Z") } })```

94) Add days in aggregation
   ``` db.events.aggregate([
      { $addFields: { expireAt: { $dateAdd: { startDate: "$createdAt", unit: "day", amount: 7 } } } }
    ])```

95) Group by day
   ``` db.logs.aggregate([
      { $group: {
          _id: { $dateToString: { format: "%Y-%m-%d", date: "$at" } },
          count: { $sum: 1 }
      }}
    ])```


P. Security & Users (admin)
---------------------------
96) Create user with readWrite on db
  ```  db.createUser({ user: "appuser", pwd: "StrongPass123", roles: [{ role: "readWrite", db: "company" }] })```

97) Update user password
```    db.updateUser("appuser", { pwd: "NewPass456" })```

98) List users
  ```  db.getUsers()```


Q. Transactions (Replica set required)
--------------------------------------
99) Session with transaction (pseudo)
  ```  const session = db.getMongo().startSession();
    session.startTransaction();
    try {
      session.getDatabase("company").accounts.updateOne({ _id: 1 }, { $inc: { balance: -100 } });
      session.getDatabase("company").accounts.updateOne({ _id: 2 }, { $inc: { balance: 100 } });
      session.commitTransaction();
    } catch (e) {
      session.abortTransaction();
      throw e;
    } finally {
      session.endSession();
    }```

R. Backup/Restore (CLI references)
----------------------------------
100) mongodump & mongorestore (run in shell, not mongosh)
     # Backup a database
   ```  mongodump --db company --out ./backup```

     # Restore a database
    ``` mongorestore --db company ./backup/company```
