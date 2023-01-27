package `in`.tutorial.sevenminworkout

object Constants {
    fun defaultExerciseList():ArrayList<ExcerciseModel>{
        val exerciseList = ArrayList<ExcerciseModel>()
        val jumpingJacks = ExcerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.ic_jumping_jacks,
            false,
            false
        )
        exerciseList.add(jumpingJacks)
        val excercise1 = ExcerciseModel(
            2,
            "Abdominal Crunch",
            R.drawable.ic_abdominal_crunch,
            false,
            false
        )
        exerciseList.add(excercise1)
        val excercise2 = ExcerciseModel(
            3,
            "High Knee Running In Place",
            R.drawable.ic_high_knees_running_in_place,
            false,
            false
        )
        exerciseList.add(excercise2)
        val excercise3 = ExcerciseModel(
            4,
            "Lunges",
            R.drawable.ic_lunge,
            false,
            false
        )
        exerciseList.add(excercise3)
        val excercise4 = ExcerciseModel(
            5,
            "Plank",
            R.drawable.ic_plank,
            false,
            false
        )
        exerciseList.add(excercise4)
        val excercise5 = ExcerciseModel(
            6,
            "Push Up",
            R.drawable.ic_push_up,
            false,
            false
        )
        exerciseList.add(excercise5)
        val excercise6 = ExcerciseModel(
            7,
            "Push Up and rotation",
            R.drawable.ic_push_up_and_rotation,
            false,
            false
        )
        exerciseList.add(excercise6)
        val excercise7 = ExcerciseModel(
            8,
            "Side Plank",
            R.drawable.ic_side_plank,
            false,
            false
        )
        exerciseList.add(excercise7)
        val excercise8 = ExcerciseModel(
            9,
            "Squat",
            R.drawable.ic_squat,
            false,
            false
        )
        exerciseList.add(excercise8)
        val excercise9 = ExcerciseModel(
            10,
            "Step up onto chair",
            R.drawable.ic_step_up_onto_chair,
            false,
            false
        )
        exerciseList.add(excercise9)
        val excercise10 = ExcerciseModel(
            11,
            "Triceps dip on chair",
            R.drawable.ic_triceps_dip_on_chair,
            false,
            false
        )
        exerciseList.add(excercise10)
        val excercise11 = ExcerciseModel(
            12,
            "Wall Sit",
            R.drawable.ic_wall_sit,
            false,
            false
        )
        exerciseList.add(excercise11)

        return exerciseList
    }
}