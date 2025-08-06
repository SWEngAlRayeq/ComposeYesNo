package app.yes_no.data.repo_impl

import app.yes_no.data.api.YesNoApi
import app.yes_no.domain.model.YesNoResponse
import app.yes_no.domain.repo.YesNoRepository
import app.yes_no.presentation.YesNoScreen
import javax.inject.Inject

class YesNoRepositoryImpl @Inject constructor(
    private val api: YesNoApi
) : YesNoRepository {
    override suspend fun getAnswer(): YesNoResponse {
        val result = api.getYesNo()
        return YesNoResponse(result.answer, result.image)
    }
}