## Example message

*Note: All messages must be contained within the "messages" array.*

```json
{
    "messages": [
        {
            "id": "sample-unactivated-services-notification",
            "title": "You need to modify your NetID account to activate essential UW Services.",
            "titleShort": null,
            "description": "Because this message's messageType is 'notification', this field is not actually needed.",
            "descriptionShort": null,
            "messageType": "notification",
            "featureImageUrl": null,
            "priority": "high",
            "recurrence": true,
            "dismissible": false,
            "filter": {
                "goLiveDate": "2017-08-01T09:30",
                "expireDate": "2017-08-02",
                "groups": ["Users - Service Activation Required"],
            },
            "data": {
                "dataUrl": "/restProxyURL/unactivatedServices",
                "dataObject": "services",
                "dataArrayFilter": {"priority":"essential", "type":"netid"},
                "dataMessageTitle": ["result", "title"],
                "dataMessageMoreInfoUrl": ["result", "url"],
            },
            "actionButton": {
                "label": "Activate services",
                "url": "my.wisc.edu/go/example/path"
            },
            "moreInfoButton": {
              "label": "Learn more",
              "url": "/learnMore"
            },
            "confirmButton": null
        }
    ]
}
```

**Attribute breakdown**

- **id**: A unique string to identify the message. This is used for tracking seen/unseen messages, dimissed notifications, and the sort order on the notifications page.
- **title**: The text to be displayed as the message's main content -- used in all message types. **Best practices:**
  - Be concise! Try to limit your message's title to ~140 characters. Shorter titles improve click-through and are less likely to cause display issues on smaller screens. *Note: Titles longer than 140 characters will be truncated (with ellipsis) to ensure consistent appearance.*
  - Use general language and avoid pronouns for broadly visible messages that may not pertain to specific users' needs (ex. "City of Madison - Declared Snow Emergency").
  - Use the word "You" when the group- or data-filtering for a message is somewhat specific (i.e. Users with unactivated accounts).
- **titleShort**: A shorter version of the message title used by the mascot announcer menu. *Required if the `messageType` is "announcement".*
- **description**: Information about an announcement -- appears on the "Features" page and in the popup announcement.
- **descriptionShort**: Brief information about an announcement -- appears in the mascot announcer menu. *Required if the `messageType` is "announcement".*
- **messageType**: Accepts either "notification" or "announcement" -- used to distinguish between the two broader categories.
- **featureImageUrl**: *(optional)* Used by popup announcements and announcements on the Features page.
- **priority**: Set to "high" if you want the message to be displayed with higher visibility (i.e. As a priority notification or popup announcement, respectively).
- **recurrence**:*(experimental, optional)* If true, even if a notification is dismissed, it will continue to reoccur in the user's home at the start of every session until the user is no longer a member of the targeted group. For example, if a user is a member of students-with-outstanding-parking-tickets, that user will be confronted with the notification at every login until they pay the fine.
- **dismissible**: *(experimental, optional)* Set to false if you want to disallow users from dismissing a notification. This should only be used for truly critical messages. If the attribute is set to true or not set at all, the notification will be dismissible.
- **filter**: A group of attributes related to filtering messages based on a user's eligibility to see this message.
  - **goLiveDate**: *(optional)* Accepts a simple ISO date, including time (as pictured). This is used to restrict displaying a message to a certain day/time.
  - **expireDate**: *(optional)* Accepts a simple ISO date, including time (as pictured). This is used to stop displaying a message at a certain day/time.
  - **groups**: An attribute to optionally show messages only to specific groups (i.e. uPortal groups). If null or empty array, the message will be shown to everyone. Contact your portal development team for more information about group filtering.
- **data**: *(optional)* A group of attributes related to external data retrieved by a dataUrl.
  - **dataUrl**: *(optional)* The message will retrieve data from the dataUrl. If data exists, it will show this message to the user. Only supports JSON.
    You would use this feature if you want to only show the message if the specific user has data. For example: Only show user if they have a certain document.
  - **dataObject**: *(optional)* Will only be looked at if `dataUrl` is present, otherwise ignored. Used as an optional further refinement from dataUrl, if you want the notification to show only if the specific object is in the data.
  - **dataArrayFilter**: Will only be looked at if `dataUrl` is present, otherwise ignored. Used as an optional further refinement from dataUrl. If your object return is an array, you can filter on the array. Does support multiple filtering criteria as shown in the example. If used in conjunction with `dataObject`, will filter to `dataObject` first.  [AngularJS array filtering documentation] (https://docs.angularjs.org/api/ng/filter/filter)
  - **dataMessageTitle** Will be used if dataUrl is specified. Used to set the title of the message from the data response from `dataUrl`.  Expects an array for where to find the title in the data response from `dataUrl`.
  - **dataMessageMoreInfoUrl** Will be used if `dataUrl` is specified and the `more info button` is configured.  Used to set the url of the `more info button`.  Expects an array for where to find the `more info button url` in the data response from `dataUrl`.
- **actionButton**: Used to display a call to action button and to provide the URL for a notification when clicked. **Required if the `messageType` is "notification".
  - **label**: The button's text
  - **url**: The URL to go to when clicked
    - **addToHome** For an "Add To Home" action button, where the user is asked to add a widget to their home layout, the url is formatted: "addToHome/{fName}", where fName = the fname of the widget.
- **moreInfoButton**: Used to display a button link to a place where the user can read more, see more, or interact with the subject of the message. Uses the same format as `actionButton`.
- **confirmButton**: Used to display a confirmation button on popup announcements. Uses the same format as `actionButton`. **Required for `messageType` "announcement" with `priority` "high".**