

	<h3>Account Information</h3>
<table>
	<tr>
		<td>First name:</td>
		<td><input type="text" name="account.firstName" value="${sessionScope.account.firstName}" /></td>
	</tr>
	<tr>
		<td>Last name:</td>
		<td><input type="text" name="account.lastName" value="${sessionScope.account.lastName}"/></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><input type="text" size="40" name="account.email" value="${sessionScope.account.email}"/></td>
	</tr>
	<tr>
		<td>Phone:</td>
		<td><input type="text" name="account.phone" value="${sessionScope.account.phone}"/></td>
	</tr>
	<tr>
		<td>Address 1:</td>
		<td><input type="text" size="40" name="account.address1" value="${sessionScope.account.address1}"/></td>
	</tr>
	<tr>
		<td>Address 2:</td>
		<td><input type="text" size="40" name="account.address2" value="${sessionScope.account.address2}"/></td>
	</tr>
	<tr>
		<td>City:</td>
		<td><input type="text"  name="account.city" value="${sessionScope.account.city}"/></td>
	</tr>
	<tr>
		<td>State:</td>
		<td><input type="text" size="4" name="account.state" value="${sessionScope.account.state}"/></td>
	</tr>
	<tr>
		<td>Zip:</td>
		<td><input type="text" size="10" name="account.zip" value="${sessionScope.account.zip}"/></td>
	</tr>
	<tr>
		<td>Country:</td>
		<td><input type="text" size="15" name="account.country" value="${sessionScope.account.country}"/></td>
	</tr>
</table>


<h3>Profile Information</h3>

<table>
	<tr>
		<td>Language Preference:</td>
		<td>
			<select name="account.languagePreference">
				<c:forEach var="languagePreference" items="${languagePre}">
					<option value="${languagePreference}" <c:if test="${languagePreference==sessionScope.account.languagePreference}">selected="selected"</c:if>>${languagePreference}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>Favourite Category:</td>
		<td>
			<select name="account.favouriteCategoryId">
				<c:forEach var="favCategoryId" items="${favCategory}">
					<option value="${favCategoryId}"
							<c:if test="${favCategoryId==sessionScope.account.favouriteCategoryId }">selected="selected"</c:if>>${favCategoryId}
					</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>Enable MyList</td>
		<td>
			<input type="checkbox" name="account.listOption"  <c:if test="${sessionScope.account.listOption}">checked="checked"</c:if>/>
		</td>
	</tr>
	<tr>
		<td>Enable MyBanner</td>
		<td>
			<input type="checkbox" name="account.bannerOption" <c:if test="${sessionScope.account.bannerOption}">checked="checked"</c:if>/>
		</td>
	</tr>

</table>

