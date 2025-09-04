# MongoDB Setup (Draft)

## 1. Download
- Go to MongoDB Community Server Download  
- Select **Windows x64 MSI** and download

## 2. Install
- Run the `.msi` installer as Administrator  
- Choose **Complete Setup**  
- Tick **Install MongoDB as a Service**  
- (Optional) Tick **MongoDB Compass**  
- Finish installation  

## 3. Create Data Folder
If not created automatically:
```cmd
md C:\data\db
4. Install MongoDB Shell (mongosh)
Download MongoDB Shell

Install it

Add bin to PATH

5. Start MongoDB
As service:


net start MongoDB
Or manually:


"C:\Program Files\MongoDB\Server\<version>\bin\mongod.exe" --dbpath="C:\data\db"
6. Connect

mongosh
MongoDB Lab Manual - Queries & Tasks (Draft)
A. Database & Collection Management

show dbs
use company
db.createCollection("employees")
db.createCollection("events", { capped: true, size: 1048576, max: 1000 })
db.employees.drop()
db.dropDatabase()
db.employees.renameCollection("myemp")
db
show collections
db.employees.insertOne({ empId: 1, name: "John", dept: "IT" })
db.employees.stats()

B. Insert Operations

db.employees.insertOne({ name: "John", age: 30, dept: "HR" })
db.employees.insertMany([{ name: "Alice", age: 25 }, { name: "Bob", age: 28 }])
db.employees.insertOne({ name: "Sara", address: { city: "NY", zip: 12345 } })
db.employees.insertOne({ name: "Mike", skills: ["Java", "Python", "MongoDB"] })
db.employees.insertOne({ _id: 1001, name: "Leo" })
db.employees.insertOne({ name: "Rita", joined: new Date(), dept: "IT" })
db.employees.updateOne({ _id: 5001 }, { $setOnInsert: { name: "Zed" } }, { upsert: true })
db.prices.insertOne({ sku: "P100", price: NumberDecimal("19.99") })
db.flags.insertOne({ feature: "beta", enabled: true })
db.logs.insertOne({ level: "INFO", at: ISODate("2024-01-01T10:00:00Z") })

C. Basic Reads

db.employees.find()
db.employees.findOne()
db.employees.find({ dept: "IT" })
db.employees.find({ dept: "IT", age: 25 })
db.employees.find({ $or: [{ dept: "IT" }, { age: 28 }] })
db.employees.find({ age: { $gt: 25 } })
db.employees.find({ age: { $lt: 30 } })
db.employees.find({ age: { $gte: 30 } })
db.employees.find({ dept: { $ne: "HR" } })
db.employees.find({ dept: { $in: ["IT", "Finance"] } })
D. Projection

db.employees.find({}, { name: 1 })
db.employees.find({}, { _id: 0, name: 1, dept: 1 })
db.employees.find({}, { address: 0 })
db.sales.aggregate([{ $project: { total: { $multiply: ["$qty", "$price"] } } }])
db.posts.find({}, { comments: { $slice: 5 } })

E. Sorting, Limiting, Skipping

db.employees.find().sort({ age: 1 })
db.employees.find().sort({ age: -1 })
db.employees.find().limit(5)
db.employees.find().skip(10)
db.employees.find().skip(20).limit(10)

F. Updates

db.employees.updateOne({ name: "John" }, { $set: { age: 31 } })
db.employees.updateMany({ dept: "IT" }, { $set: { status: "active" } })
db.employees.updateOne({ name: "John" }, { $inc: { age: 1 } })
db.employees.updateMany({}, { $rename: { dept: "department" } })
db.employees.updateMany({}, { $unset: { status: "" } })
db.products.updateMany({}, { $mul: { price: 1.1 } })
db.stats.updateOne({ _id: 1 }, { $min: { low: 10 }, $max: { high: 100 } })
db.docs.updateOne({ _id: 1 }, { $currentDate: { updatedAt: true } })
db.employees.updateOne({ name: "Nora" }, { $set: { dept: "Ops" } }, { upsert: true })
db.employees.updateOne({ name: "Mike" }, { $addToSet: { skills: "Docker" } })

G. Deletes

db.employees.deleteOne({ name: "John" })
db.employees.deleteMany({ dept: "Finance" })
db.temp.deleteMany({})

H. Array Queries & Updates

db.employees.find({ skills: "Java" })
db.scores.find({ points: { $elemMatch: { $gt: 80 } } })
db.employees.find({ skills: { $size: 3 } })
db.employees.updateOne({ name: "Mike" }, { $push: { skills: "AWS" } })
db.employees.updateOne({ name: "Mike" }, { $pull: { skills: "Java" } })
db.arr.updateOne({ _id: 1 }, { $push: { vals: { $each: [5,3,9], $sort: 1 } } })
db.arr.updateOne({ _id: 1 }, { $pop: { vals: 1 } })
I. Aggregation Basics

db.employees.countDocuments()
db.employees.aggregate([{ $group: { _id: "$dept", total: { $sum: 1 } } }])
db.employees.aggregate([{ $group: { _id: "$dept", avgAge: { $avg: "$age" } } }])
db.employees.aggregate([{ $group: { _id: null, maxSalary: { $max: "$salary" } } }])
db.employees.aggregate([{ $group: { _id: null, minSalary: { $min: "$salary" } } }])
db.employees.aggregate([{ $match: { age: { $gt: 25 } } }, { $group: { _id: "$dept", count: { $sum: 1 } } }])
db.employees.aggregate([{ $sort: { age: -1 } }])
db.employees.aggregate([{ $project: { name: 1, dept: 1, _id: 0 } }])
db.sales.aggregate([{ $addFields: { total: { $multiply: ["$qty", "$price"] } } }])
db.employees.aggregate([{ $limit: 10 }])

J. Aggregation – Joins & Arrays
db.employees.aggregate([
  { $lookup: { from: "departments", localField: "deptId", foreignField: "_id", as: "deptInfo" } }
])

db.employees.aggregate([{ $unwind: "$skills" }])

db.orders.aggregate([
  { $lookup: { from: "customers", localField: "custId", foreignField: "_id", as: "cust" } },
  { $unwind: "$cust" },
  { $project: { _id: 0, orderNo: 1, customer: "$cust.name" } }
])

db.employees.aggregate([
  { $unwind: "$skills" },
  { $group: { _id: "$skills", count: { $sum: 1 } } },
  { $sort: { count: -1 } }
])

db.employees.aggregate([
  { $facet: {
      byDept: [ { $group: { _id: "$dept", total: { $sum: 1 } } } ],
      ages:   [ { $bucket: { groupBy: "$age", boundaries: [20,30,40,50,60], default: "60+" } } ]
  }}
])

K. Indexing & Performance

db.employees.createIndex({ name: 1 })
db.employees.createIndex({ dept: 1, age: -1 })
db.articles.insertMany([
  { title: "MongoDB Basics", body: "Learn MongoDB" },
  { title: "Advanced MongoDB", body: "Indexes, Aggregation" }
])
db.articles.createIndex({ title: "text", body: "text" })
db.places.createIndex({ location: "2dsphere" })
db.employees.getIndexes()
db.employees.dropIndex("name_1")
db.employees.find({ name: "John" }).explain("executionStats")

L. Text & Regex Search

db.articles.find({ $text: { $search: "MongoDB performance" } })
db.articles.find(
  { $text: { $search: "scaling" } },
  { score: { $meta: "textScore" } }
).sort({ score: { $meta: "textScore" } })
db.employees.find({ name: /^A/ })
db.employees.find({ name: /john/i })
M. Schema & Validation

db.createCollection("validated", {
  validator: { $jsonSchema: {
    bsonType: "object",
    required: ["name", "age"],
    properties: {
      name: { bsonType: "string" },
      age:  { bsonType: "int", minimum: 0 }
    }
  }}
})

db.runCommand({
  insert: "validated",
  documents: [{ name: "Temp", age: 1 }],
  bypassDocumentValidation: true
})
N. Distinct, Exists, Types

db.employees.distinct("dept")
db.employees.find({ manager: { $exists: true } })
db.employees.find({ age: { $type: "int" } })
db.employees.find({ middleName: null })
db.employees.find({ middleName: { $exists: false } })
O. Dates & Time

db.logs.find({ at: { $gte: ISODate(new Date().toISOString().slice(0,10) + "T00:00:00Z") } })

db.events.aggregate([
  { $addFields: { expireAt: { $dateAdd: { startDate: "$createdAt", unit: "day", amount: 7 } } } }
])

db.logs.aggregate([
  { $group: {
      _id: { $dateToString: { format: "%Y-%m-%d", date: "$at" } },
      count: { $sum: 1 }
  }}
])
P. Security & Users

db.createUser({ user: "appuser", pwd: "StrongPass123", roles: [{ role: "readWrite", db: "company" }] })
db.updateUser("appuser", { pwd: "NewPass456" })
db.getUsers()
Q. Transactions (Replica set required)

const session = db.getMongo().startSession();
session.startTransaction();
try {
  session.getDatabase("company").accounts.updateOne({ _id: 1 }, { $inc: { balance: -100 } });
  session.getDatabase("company").accounts.updateOne({ _id: 2 }, { $inc: { balance: 100 } });
  session.commitTransaction();
} catch (e) {
  session.abortTransaction();
}
session.endSession();
R. Backup/Restore (CLI)

mongodump --db company --out ./backup
mongorestore --db company ./backup/company
