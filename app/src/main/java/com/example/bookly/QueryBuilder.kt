package com.example.bookly

object QueryBuilder {
    fun buildQuery(
        selectedGenres:      Map<String, Boolean>,
        selectedDates:       Map<String, Boolean>,
        selectedNonfiction:  Map<String, Boolean>,
        selectedFantasy:     Map<String, Boolean>,
        selectedHorror:      Map<String, Boolean>,
        selectedScifi:       Map<String, Boolean>,
        selectedRomance:     Map<String, Boolean>
    ): String {
        val excludedSubjects = listOf("Juvenile fiction", "Juvenile literature", "Children",
            "Study guides", "Coloring books", "Activity books",
            "Textbooks", "Examinations", "Workbooks")
        val exclusionClause = excludedSubjects.joinToString(" AND "){
            "(NOT subject:\$it\")"
        }
        val subjectClauses = mutableListOf<String>().apply {
            selectedGenres      .filterValues { it }.keys.forEach { add("subject:\"$it\"") }
            selectedNonfiction  .filterValues { it }.keys.forEach { add("(subject:\"$it\" AND subject:Nonfiction)") }
            selectedFantasy     .filterValues { it }.keys.forEach { add("(subject:\"$it\" AND subject:Fantasy)") }
            selectedHorror      .filterValues { it }.keys.forEach { add("(subject:\"$it\" AND subject:Horror)") }
            selectedScifi       .filterValues { it }.keys.forEach { add("(subject:\"$it\" AND subject:Science Fiction)") }
            selectedRomance     .filterValues { it }.keys.forEach { add("(subject:\"$it\" AND subject:Romance)") }
        }

        val yearClauses = mutableListOf<String>().apply {
            if (selectedDates["Published in 20th Century"] == true) add("first_publish_year:[* TO 1999]")
            if (selectedDates["Published in 21st Century to 2015"] == true) add("first_publish_year:[2000 TO 2015]")
            if (selectedDates["Published 2016 to now"] == true) add("first_publish_year:[2016 TO *]")
        }

        val parts = mutableListOf<String>()
        if (subjectClauses.isNotEmpty()) {
            parts += "(" + subjectClauses.joinToString(" OR ") + ")"
        }
        if (yearClauses.isNotEmpty()) {
            parts += "(" + yearClauses.joinToString(" OR ") + ")"
        }

        parts += exclusionClause

        return parts.joinToString(" AND ")
            .takeIf { it.isNotBlank() }
            ?: ""
    }
}