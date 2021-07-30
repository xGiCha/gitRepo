package gr.repo.gitrero.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import gr.repo.gitrero.models.commits.Commit
import gr.repo.gitrero.models.commits.Commits
import gr.repo.gitrero.models.commits.CommitsItem
import gr.repo.gitrero.util.Constants.Companion.REPOS_TABLE
import gr.repo.gitrero.util.formatDateShort
import java.util.*

@Entity(tableName = REPOS_TABLE)
class ReposEntity(
        var commitList: Commits,
        @PrimaryKey(autoGenerate = false)
        var id: String
) {
    var date = formatDateShort(Date())
}