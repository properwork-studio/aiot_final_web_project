## AIOT FINAL PROJECT BACKEND

#### URL Pattern Design:
	(v)"/" : index page (index.jsp)

	(v)"/login" : login page (login.jsp - show login form)

	(v)"/toLogin" : to do the login verification (loginFilter - if success then change the dbconnection to the current user database)

	(v)"toLogout" : to do the logout action (redirect to login page)

	(v)"/setting_account" : create new account page (init_account.jsp - show create account form)

	(v)"/new_account" : to do the account creation (AccountServlet - check if the email is not duplicate, if success then change the dbconnection to the current user database, create a new database and tables inside it - members, contact, medicines, medicineRecords, doorRecords, fallRecords)

	(v)"/setting_member" : add new member page (init_member.jsp - show member initialization form)

	"/new_member" : to do the member creation
	"/next_member" : to do the member creation and show the form again

	"/setting_contact" : add new contact page (init_contact.jsp - show contact initialization form)

	"/new_contact" : to do the contact creation

	"/edit_contact" : to do the contact update

	"/setting_medicines" : add new medicine rule page (init_medicine.jsp - show medicine initialization form)

	"/new_medicine" : to do the medicine rule creation from dashboard
	"/init_medicine" : to do the final medicine rule creation at initial phase and will go to dashboard
	"/next_medicine" : to do the medicine rule creation at initial phase, and will back to init_medicine page

	"/edit_medicine" : to do the medicine rule update

	"/delete_medicine" : to do the medicine rule delete

	"/dashboard" : dashboard page - to show all the information (dashboard.jsp)
