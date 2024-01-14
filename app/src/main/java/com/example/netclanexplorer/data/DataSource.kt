package com.example.netclanexplorer.data

import com.example.netclanexplorer.model.Person

object DataSource {
    private const val commonTitle = "Hi! I am open for new connections \uD83D\uDE0A\\"
    val personList = listOf(
        Person(
            firstName = "John",
            middleName = null,
            lastName = "Doe",
            address = "123 Main Street",
            locationRange = 500, // Example: 500 meters
            title = commonTitle,
            description = "John Doe is a seasoned Software Engineer with a rich background in developing cutting-edge solutions. His passion lies in leveraging technology to address complex challenges, and he excels at crafting innovative software that pushes the boundaries of what's possible. John is dedicated to staying abreast of the latest industry trends, ensuring that his work remains at the forefront of technological advancements. Outside of the coding realm, John enjoys sharing his knowledge through mentorship and contributing to open-source projects."
        ),
        Person(
            firstName = "Jane",
            middleName = "Deck",
            lastName = "Smith",

            address = "456 Oak Avenue",
            locationRange = 1000, // Example: 1000 meters
            title = commonTitle,
            description = "Meet Jane Smith, a dynamic Marketing Specialist renowned for her creativity and strategic acumen. Jane brings a wealth of experience in crafting and executing impactful marketing campaigns that resonate with target audiences. Her keen understanding of market trends, coupled with an innovative approach, has consistently driven positive results for the brands she collaborates with. Outside of the professional realm, Jane is passionate about community engagement and frequently volunteers her time to support local initiatives."
        ),
        Person(
            firstName = "Chris",
            middleName = null,
            lastName = "Johnson",
            address = "789 Pine Road",
            locationRange = 800, // Example: 800 meters
            title = commonTitle,
            description = "Chris Johnson is a meticulous Financial Analyst known for his expertise in dissecting complex financial data to provide actionable insights. With a keen eye for detail and a strategic mindset, Chris has a track record of guiding organizations toward informed financial decisions. His analytical prowess extends beyond the numbers, as Chris is also adept at communicating financial concepts in a clear and accessible manner. In his spare time, Chris enjoys staying active and exploring new outdoor adventures."
        )
    )

    val businessPersonsList = listOf(
        Person(
            firstName = "Manish",
            middleName = "Raj",
            lastName = "Yadav",
            address = "Kathmandu",
            locationRange = 200,
            title = "Hello community I am available at your service!",
            description = null


        )
    )
}
