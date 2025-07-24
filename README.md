# 📚 Bookly
Bookly is a sleek Android app that helps you discover new books using a swipeable interface and powerful genre filtering. Think of it as Tinder for book lovers with custom lists, detailed book descriptions, and ratings sourced from the Open Library.

---

## 🔨 Getting Started
### Prerequisites
* Android Sutdio Narwhal or later
* Kotlin 1.9+

### Installation
```
git clone https://github.com/your-username/bookly.git
cd bookly
```
Open in Android Studio and run on a device or emulator.

## ✨ Features

### 🔍 Discover New Reads
Swipe through books fetched live from the Open Library API.

### ⏱️ Quick Reactions
Mark books as:
* Read and Liked
* Read and Disliked
* Unread and Interested
* Unread and Not Interested

### 📁 Organize with My Lists
View and manage books by reaction category using a custom top tab bar.

### 🧠 Advanced Filters
* Filter by genre, specific subgenre elements, and publication date ranges
* Dynamically built Open Library query with ```QueryBuilder.kt```

### 💬 Responsive UI Feedback
* Displays loading indicators:
  * Books are being fetched from the API
  * Book covers are loading
  * New filters are being applied

## 🛠 Tech Stack
* Language: Kotlin
* UI Toolkit: Jetpack Compose

## 🌍 API Refernce
Bookly uses Open Library's Search API:
* ```/search.json?q=query``` -- keyword search
* ```/works/{id}.json``` -- book details
* ```/works/{id}/ratings.json``` -- average rating

## 🔄 Future Updates

### 📖 Book Detail View
Make each book on the Discover screen tappable to:
* Open a dedicated screen with a full description, author bio (if available), and more rating details

### 🧐 Better Suggestions
Suggest more relevant books by:
* Removing work books, activity books, study guides, textbooks, etc.

### ✅ Serializable
The user's data will be saved such as:
* The user's lists
* The user's last saved filtering preferences

## 🤔 Potential Ideas

### 📊 Data and Insights
User's can see insights of their preferences such as:
* The most popular genres that they are interested in
* The genres that the user seems the least interested in

