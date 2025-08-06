package app.yes_no.domain.repo

import app.yes_no.domain.model.YesNoResponse

interface YesNoRepository {
    suspend fun getAnswer(): YesNoResponse
}