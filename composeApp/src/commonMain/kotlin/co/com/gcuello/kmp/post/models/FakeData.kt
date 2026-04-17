package co.com.gcuello.kmp.post.models

import co.com.gcuello.kmp.post.ui.uiModels.CommentUi
import co.com.gcuello.kmp.post.ui.uiModels.PostUi
import kotlin.random.Random

object FakeData {
    fun posts(count: Int = 20): List<PostUi> =
        (1..count).map { id ->
            PostUi(
                id = id,
                title = sampleTitles.random(),
                body = sampleBodies.random()
            )
        }

    fun comments(postId: Int, count: Int = 12): List<CommentUi> =
        (1..count).map { idx ->
            CommentUi(
                id = (postId * 1000L) + idx,
                postId = postId,
                name = sampleNames.random(),
                email = sampleEmails.random(),
                body = sampleComments.random()
            )
        }

    private val sampleTitles = listOf(
        "Nueva funcionalidad disponible",
        "Actualización del sistema",
        "Notas de la versión",
        "Recomendaciones para mejorar el rendimiento",
        "Diseño moderno en Compose",
        "Offline-first: lo que debes saber",
        "MVVM + Room en un solo lugar",
        "Mejores prácticas con StateFlow"
    )

    private val sampleBodies = listOf(
        "Este post explica de forma breve cómo organizar tu app para que escale sin dolor. Incluye capas, casos de uso y repositorios.",
        "Un enfoque offline-first permite que el usuario trabaje incluso sin red, sincronizando cuando vuelva la conexión.",
        "Jetpack Compose facilita interfaces declarativas. Con Material 3 puedes lograr un look premium con poco esfuerzo.",
        "Una base local con Room te permite persistencia confiable. Combínalo con Flow para UI reactiva."
    )

    private val sampleComments = listOf(
        "Me encanta el diseño, se siente súper moderno.",
        "¿Esto funciona sin conexión? Si es así, 10/10.",
        "Buenísimo, gracias por compartir.",
        "¿Podrías agregar paginación en el listado?",
        "La arquitectura se ve clara y escalable.",
        "Compose + Material 3 queda excelente con ese degradado."
    )

    private val sampleNames = listOf(
        "Gianca", "María", "Juan", "Sofía", "Andrés", "Valentina", "Camilo", "Laura"
    )

    private val sampleEmails = listOf(
        "gianca@email.com",
        "maria@email.com",
        "juan@email.com",
        "sofia@email.com",
        "andres@email.com"
    )
}