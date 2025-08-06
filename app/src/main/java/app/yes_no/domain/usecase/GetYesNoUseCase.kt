package app.yes_no.domain.usecase

import app.yes_no.domain.model.YesNoResponse
import app.yes_no.domain.repo.YesNoRepository
import javax.inject.Inject

class GetYesNoUseCase @Inject constructor(
    private val repository: YesNoRepository
) {
    suspend operator fun invoke(): YesNoResponse {
        return repository.getAnswer()
    }
}