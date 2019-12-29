build:
	cd db && $(MAKE)
	cd server && $(MAKE)

push:
	cd server && $(MAKE) push
