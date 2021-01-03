from string import ascii_lowercase

rootperm = "benchat"
chatperm = "quickchat"
chatpermsend = "send"
chatpermget = "get"


print("name: BenChat")
print("main: dev.benjaminc.benchat.BenChatMain")
print("version: 1.0")
print("api-version: 1.16")
print("commands:")
print("  " + chatperm + ":")
print("    description: Gives access to all BenChat features")
als = "";
sep = False;
for c in ascii_lowercase:
	if sep:
		als = als + ", "
	else:
		sep = True;
	als = als + c
print("    aliases: [" + als + "]")
print("")
print("permissions:")
print("  " + rootperm + ".*:")
print("    description: Gives access to all BenChat features")
print("    children:")
for c in ascii_lowercase:
	print("      " + rootperm + "." + chatperm + "." + chatpermsend + "." + c + ": false");
	print("      " + rootperm + "." + chatperm + "." + chatpermget  + "." + c + ": false");

for c in ascii_lowercase:
	print("  " + rootperm + "." + chatperm + "." + chatpermsend + "." + c + ":")
	print("    description: Quickchat send to group " + c)
	print("    default: false")
	print("  " + rootperm + "." + chatperm + "." + chatpermget  + "." + c + ":")
	print("    description: QuickChat get from group " + c)
	print("    default: false")
